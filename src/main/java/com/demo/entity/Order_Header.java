package com.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order_Header {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer orderId;

	    @Column(name = "order_date", nullable = false)
	    private java.util.Date orderDate;

	    @ManyToOne
	    @JoinColumn(name = "customer_id", nullable = false)
	    private Customer customer;

	    @ManyToOne
	    @JoinColumn(name = "shipping_contact_mech_id", nullable = false)
	    private Contact_Mech shippingContactMech;

	    @ManyToOne
	    @JoinColumn(name = "billing_contact_mech_id", nullable = false)
	    private Contact_Mech billingContactMech;
	    
	    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonManagedReference 
	    private List<Order_Item> orderItems;
}
