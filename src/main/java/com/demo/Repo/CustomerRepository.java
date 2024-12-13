package com.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
