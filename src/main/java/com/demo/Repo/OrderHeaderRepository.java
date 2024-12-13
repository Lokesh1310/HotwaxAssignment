package com.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Order_Header;

public interface OrderHeaderRepository extends JpaRepository<Order_Header, Integer> {

}
