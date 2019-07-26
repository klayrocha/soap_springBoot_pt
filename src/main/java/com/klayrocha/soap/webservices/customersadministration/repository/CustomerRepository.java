package com.klayrocha.soap.webservices.customersadministration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klayrocha.soap.webservices.customersadministration.bean.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
