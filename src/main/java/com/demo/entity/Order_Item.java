package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order_Item {


	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer orderItemSeqId;

	   @ManyToOne
	   @JoinColumn(name="order_id", nullable=false)
	   @JsonBackReference
	   private Order_Header orderHeader;

	   @ManyToOne
	   @JoinColumn(name="product_id", nullable=false)
	   private Product product;

	   @Column(nullable=false)
	   private Integer quantity;

	   @Column(nullable=false, length=20)
	   private String status;
}
