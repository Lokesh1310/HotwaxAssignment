package com.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Contact_Mech;

public interface ContactMechRepository extends JpaRepository<Contact_Mech, Integer> {

}
