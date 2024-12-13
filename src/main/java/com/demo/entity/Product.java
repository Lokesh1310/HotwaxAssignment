package com.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer productId;

	    @Column(name = "product_name", nullable = false, length = 100)
	    private String productName;

	    @Column(name = "color", length = 30)
	    private String color;

	    @Column(name = "size", length = 10)
	    private String size;

		public Product(Integer productId) {
			super();
			this.productId = productId;
		}

	    
	    
}
