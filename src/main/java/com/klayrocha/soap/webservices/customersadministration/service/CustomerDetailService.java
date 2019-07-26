package com.klayrocha.soap.webservices.customersadministration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.klayrocha.soap.webservices.customersadministration.bean.Customer;
import com.klayrocha.soap.webservices.customersadministration.helper.Status;
import com.klayrocha.soap.webservices.customersadministration.repository.CustomerRepository;


@Component
public class CustomerDetailService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer findById(Integer id) {
		Optional<Customer> customerOptional = customerRepository.findById(id);
		if(customerOptional.isPresent()) {
			return customerOptional.get();
		}
		return null;
	}
	
	public List<Customer> findAll(){
		return customerRepository.findAll();
	}
	
	public Status deleteById(Integer id) {
		try {
			customerRepository.deleteById(id);
			return Status.SUCCESS;
		}catch(Exception ex) {
			return Status.FAILURE;
		}
	}
	
	public Status insert(Customer customer) {
		try {
			customerRepository.save(customer);
			return Status.SUCCESS;
		}catch(Exception ex) {
			return Status.FAILURE;
		}
	}
}
