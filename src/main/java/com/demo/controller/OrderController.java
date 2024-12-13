package com.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.AddOrderItemRequest;
import com.demo.dto.OrderRequest;
import com.demo.dto.UpdateOrderRequest;
import com.demo.entity.Order_Header;
import com.demo.entity.Order_Item;
import com.demo.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired 
	private OrderService orderService;
	
	
	@PostMapping
	public ResponseEntity postorders(@RequestBody OrderRequest orderRequest) {

		try {
            Order_Header order = orderService.createOrder(orderRequest);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
	}
	
	
	
	@GetMapping("/{id}")
	public Order_Header getOrder(@PathVariable Integer id) {
		 
		Order_Header orderById = orderService.getOrderById(id);
		
		if(orderById!=null) {
		return 	orderById;
		}
		else {
return null;
		}
	 
	}
	
	
	@PutMapping("/{Id}")
    public ResponseEntity<Order_Header> updateOrder(@PathVariable Integer Id,
                                                   @RequestBody UpdateOrderRequest updateRequest) {
        try {
            Order_Header updatedOrder = orderService.updateOrder(Id, updateRequest);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void>   deleteOrder(@PathVariable Integer id) {
    


try {
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    
    }
    
    
    @PostMapping("/{id}/items")
    public ResponseEntity<Order_Item> addOrderItem(@PathVariable Integer id,
                                                   @RequestBody AddOrderItemRequest addOrderItemRequest) {
        try {
            Order_Item createdOrderItem = orderService.addOrderItem(id, addOrderItemRequest);
            return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
    @DeleteMapping("/{orderId}/items/{order_item_seq_id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Integer orderId,
                                                 @PathVariable Integer order_item_seq_id) {
        try {
            orderService.deleteOrderItem(orderId, order_item_seq_id);
            return new ResponseEntity<>("Successfully Deleted",HttpStatus.NO_CONTENT); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
            }
    }
    
    
    @PutMapping("{order_id}/items/{order_item_seq_id}")
    public String UpateIteminOrder(@PathVariable Integer order_id,Integer order_item_seq_id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }
    
}
