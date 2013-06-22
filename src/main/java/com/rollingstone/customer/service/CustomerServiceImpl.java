package com.rollingstone.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.rollingstone.customer.dao.ICustomerDao;
import com.rollingstone.customer.model.Customer;
import com.rollingstone.customer.model.ResponseObject;

@Service
public class CustomerServiceImpl implements ICustomerService{

	ICustomerDao customerDao;
	
	public ICustomerDao getCustomerDao() {
		return customerDao;
	}

	@Autowired
	@Required
	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public Customer addCustomer(Customer customer) throws Exception {
		return customerDao.addCustomer(customer);
	}

	public ResponseObject<Customer> getAllCustomers(int pageNum, int pageSize) {
		return customerDao.getAllCustomers(pageNum, pageSize);
	}

	public boolean removeCustomer(int customerId) throws Exception {
		return customerDao.removeCustomer(customerId);
	}

	public boolean updateCustomer(Customer customer) throws Exception {
		return customerDao.updateCustomer(customer);
	}

	public ResponseObject<Customer> getSearchCustomers(int pageNum, int pageSize, String customerName, String houseNumber, String street) {
		if (customerName == null){
			customerName = "";
		}
		if (houseNumber == null){
			houseNumber = "";
		}
		if (street == null){
			street = "";
		}
		
		return customerDao.getSearchCustomers(pageNum, pageSize, customerName, houseNumber, street);
	}
}
