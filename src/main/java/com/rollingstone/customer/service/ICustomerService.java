package com.rollingstone.customer.service;

import com.rollingstone.customer.model.Customer;
import com.rollingstone.customer.model.ResponseObject;

public interface ICustomerService {

	Customer addCustomer(Customer customer) throws Exception;
	
	ResponseObject<Customer> getAllCustomers(int pageNum, int pageSize);
	
	boolean removeCustomer(int customerId) throws Exception;
	
	boolean updateCustomer(Customer customer) throws Exception;

	ResponseObject<Customer> getSearchCustomers(int pageNum, int pageSize,
			String customerName, String houseNumber, String street);
}
