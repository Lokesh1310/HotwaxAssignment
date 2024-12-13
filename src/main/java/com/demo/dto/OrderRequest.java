package com.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
    private String orderDate;
    private Integer customerId;
    private Integer shippingContactMechId;
    private Integer billingContactMechId;
    private List<OrderItemRequest> orderItems;

  
}