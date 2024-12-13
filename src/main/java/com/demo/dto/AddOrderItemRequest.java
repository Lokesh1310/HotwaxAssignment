package com.demo.dto;

import lombok.Data;

@Data
public class AddOrderItemRequest {

	 private Integer productId;
	    private Integer quantity;
	    private String status;
}
