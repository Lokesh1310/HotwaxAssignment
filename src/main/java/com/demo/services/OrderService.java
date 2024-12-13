package com.demo.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Repo.ContactMechRepository;
import com.demo.Repo.CustomerRepository;
import com.demo.Repo.OrderHeaderRepository;
import com.demo.Repo.OrderItemRepository;
import com.demo.Repo.ProductRepository;
import com.demo.dto.AddOrderItemRequest;
import com.demo.dto.OrderItemRequest;
import com.demo.dto.OrderRequest;
import com.demo.dto.UpdateOrderRequest;
import com.demo.entity.Contact_Mech;
import com.demo.entity.Customer;
import com.demo.entity.Order_Header;
import com.demo.entity.Order_Item;
import com.demo.entity.Product;

@Service
public class OrderService {
	@Autowired
	
    private OrderHeaderRepository orderHeaderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ContactMechRepository contactMechRepository;

    @Autowired
    private ProductRepository productRepository;
    
    public Order_Header createOrder(OrderRequest orderRequest) throws Exception {
    	SimpleDateFormat format=new  SimpleDateFormat("yyyy-MM-dd");
    	  
    	Date orderDate = format.parse(orderRequest.getOrderDate());

    	 Order_Header orderHeader = new Order_Header();
    	 orderHeader.setOrderDate(orderDate);
         
    	 
    	 Optional<Customer> cId = customerRepository.findById(orderRequest.getCustomerId());
    	
    	 if(cId.isPresent()) {
    		 orderHeader.setCustomer(cId.get());
    	 }
    	 else {
    		 throw new  Exception();
    	 }
    	 
    	 
    	 Optional<Contact_Mech> cmId = contactMechRepository.findById(orderRequest.getShippingContactMechId());
    	 if(cmId.isPresent()) {
    		 
    		 orderHeader.setShippingContactMech(cmId.get());
    	 }
    	 else {
    		 throw new  Exception();
    	 }
    	 
    	 
    	 Optional<Contact_Mech> cmeId = contactMechRepository.findById(orderRequest.getBillingContactMechId());
    
    	 if(cmeId.isPresent()) {
    		 orderHeader.setBillingContactMech(cmeId.get());
    	 }
    	 else {
    		 throw new  Exception();

    	 }
    	 
    	 
    	 
         Order_Header savedOrder = orderHeaderRepository.save(orderHeader);

     
         for (OrderItemRequest item : orderRequest.getOrderItems()) {
             Order_Item orderItem = new Order_Item();
             orderItem.setOrderHeader(savedOrder);
             orderItem.setProduct(new Product(item.getProductId()));
             orderItem.setQuantity(item.getQuantity());
             orderItem.setStatus(item.getStatus());
             orderItemRepository.save(orderItem);
         }

         return savedOrder;
    }

	public Order_Header getOrderById(Integer id) {
		
		Optional<Order_Header> byId = orderHeaderRepository.findById(id);
		
		
			return byId.get();
		
		
			
		
	}

	public Order_Header updateOrder(Integer id, UpdateOrderRequest updateRequest) {


		Order_Header orderHeader = orderHeaderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));		

		   Contact_Mech shippingContact = contactMechRepository.findById(updateRequest.getShippingContactMechId())
                   .orElseThrow(() -> new RuntimeException("Shipping contact not found"));
           orderHeader.setShippingContactMech(shippingContact);
		
           
           Contact_Mech billingContact = contactMechRepository.findById(updateRequest.getBillingContactMechId())
                   .orElseThrow(() -> new RuntimeException("Billing contact not found"));
           orderHeader.setBillingContactMech(billingContact);
	
           return orderHeaderRepository.save(orderHeader);
	}

	public void deleteOrder(Integer id) {
		  Order_Header orderHeader = orderHeaderRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Order not found"));
	        orderHeaderRepository.deleteById(id);	
	}

	public Order_Item addOrderItem(Integer orderId, AddOrderItemRequest addOrderItemRequest) {
		Order_Header orderHeader = orderHeaderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

		 Product product = productRepository.findById(addOrderItemRequest.getProductId())
	                .orElseThrow(() -> new RuntimeException("Product not found"));
		
		 Order_Item orderItem = new Order_Item();
	        orderItem.setOrderHeader(orderHeader);
	        orderItem.setProduct(product);
	        orderItem.setQuantity(addOrderItemRequest.getQuantity());
	        orderItem.setStatus(addOrderItemRequest.getStatus());
	        
	        return orderItemRepository.save(orderItem);
	}

	public void deleteOrderItem(Integer orderId, Integer order_item_seq_id) {


   Order_Header orderHeader = orderHeaderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));


    Order_Item orderItem = orderItemRepository.findById(order_item_seq_id)
                .orElseThrow(() -> new RuntimeException("Order item not found"));


        if (!orderItem.getOrderHeader().getOrderId().equals(orderId)) {
            throw new RuntimeException("Order item does not belong to this order");
        }


        orderItemRepository.delete(orderItem);
		
	}

    
	
}
