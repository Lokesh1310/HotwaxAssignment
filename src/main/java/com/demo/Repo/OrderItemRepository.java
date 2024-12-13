package com.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Order_Item;

public interface OrderItemRepository extends JpaRepository<Order_Item , Integer> {

}
