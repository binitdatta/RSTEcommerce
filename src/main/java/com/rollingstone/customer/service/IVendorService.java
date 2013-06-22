package com.rollingstone.customer.service;

import java.util.List;

import com.rollingstone.customer.model.Vendor;

public interface IVendorService {

	Vendor addVendor(Vendor vendor) throws Exception;
	
	List<Vendor> getAllProducts();
	
	boolean removeVendor(int vendorId) throws Exception;
	
	boolean updateVendor(Vendor vendor) throws Exception;
}
