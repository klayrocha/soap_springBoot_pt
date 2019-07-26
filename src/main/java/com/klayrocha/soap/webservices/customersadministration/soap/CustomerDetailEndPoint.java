package com.klayrocha.soap.webservices.customersadministration.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.klayrocha.soap.webservices.customersadministration.bean.Customer;
import com.klayrocha.soap.webservices.customersadministration.service.CustomerDetailService;
import com.klayrocha.soap.webservices.customersadministration.soap.exception.CustomerNotFoundException;

import br.com.klayrocha.CustomerDetail;
import br.com.klayrocha.DeleteCustomerRequisicao;
import br.com.klayrocha.DeleteCustomerResposta;
import br.com.klayrocha.GetAllCustomerDetailRequisicao;
import br.com.klayrocha.GetAllCustomerDetailResposta;
import br.com.klayrocha.GetCustomerDetailRequisicao;
import br.com.klayrocha.GetCustomerDetailResposta;
import br.com.klayrocha.InsertCustomerRequisicao;
import br.com.klayrocha.InsertCustomerResposta;

@Endpoint
public class CustomerDetailEndPoint {
	
	@Autowired
	CustomerDetailService service;
	
	@PayloadRoot(namespace="http://klayrocha.com.br", localPart="GetCustomerDetailRequisicao")
	@ResponsePayload
	public GetCustomerDetailResposta processCustomerDetailRequisicao(@RequestPayload GetCustomerDetailRequisicao req) throws Exception {
		Customer customer = service.findById(req.getId());
		if(customer == null) {
			throw new CustomerNotFoundException("Invalid Customer id "+req.getId());
		}
		return convertToGetCustomerDetailResposta(customer);
	}
	
	private GetCustomerDetailResposta convertToGetCustomerDetailResposta(Customer customer) {
		GetCustomerDetailResposta resp = new GetCustomerDetailResposta();
		resp.setCustomerDetail(convertToCustomerDetail(customer));
		return resp;
	}
	
	private CustomerDetail convertToCustomerDetail(Customer customer) {
		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setId(customer.getId());
		customerDetail.setName(customer.getName());
		customerDetail.setPhone(customer.getPhone());
		customerDetail.setEmail(customer.getEmail());
		return customerDetail;
	}
	
	@PayloadRoot(namespace="http://klayrocha.com.br", localPart="GetAllCustomerDetailRequisicao")
	@ResponsePayload
	public GetAllCustomerDetailResposta processGetAllCustomerDetailRequisicao(@RequestPayload GetAllCustomerDetailRequisicao req) {
		List<Customer> customers = service.findAll();
		return convertToGetAllCustomerDetailResposta(customers);
	}
	
	private GetAllCustomerDetailResposta convertToGetAllCustomerDetailResposta(List<Customer> customers) {
		GetAllCustomerDetailResposta resp = new GetAllCustomerDetailResposta();
		customers.stream().forEach(c -> resp.getCustomerDetail().add(convertToCustomerDetail(c)));
		return resp;
	}
	
	@PayloadRoot(namespace="http://klayrocha.com.br", localPart="DeleteCustomerRequisicao")
	@ResponsePayload
	public DeleteCustomerResposta deleteCustomerRequisicao(@RequestPayload DeleteCustomerRequisicao req) {
		DeleteCustomerResposta resp = new DeleteCustomerResposta();
		resp.setStatus(convertStatusSoap(service.deleteById(req.getId())));
		return resp;
	}
	
	private br.com.klayrocha.Status convertStatusSoap(
			com.klayrocha.soap.webservices.customersadministration.helper.Status statusService) {
		if(statusService == com.klayrocha.soap.webservices.customersadministration.helper.Status.FAILURE) {
			return br.com.klayrocha.Status.FAILURE;
		}
		return br.com.klayrocha.Status.SUCCESS;
	}
	
	@PayloadRoot(namespace="http://klayrocha.com.br", localPart="InsertCustomerRequisicao")
	@ResponsePayload
	public InsertCustomerResposta insertCustomerRequisicao(@RequestPayload InsertCustomerRequisicao req) {
		InsertCustomerResposta resp = new InsertCustomerResposta();
		resp.setStatus(convertStatusSoap(service.insert(convertCustomer(req.getCustomerDetail()))));
		return resp;
	}
	
	private Customer convertCustomer(CustomerDetail customerDetail) {
		return new Customer(customerDetail.getId(),customerDetail.getName(),customerDetail.getPhone(),customerDetail.getEmail());
	}
	
}
