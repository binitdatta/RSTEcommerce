package com.rollingstone.customer.dao;

import java.util.List;

import com.rollingstone.customer.model.Vendor;

public interface IVendorDao {

Vendor addVendor(Vendor vendor) throws Exception;
	
	List<Vendor> getAllVendors();
	
	boolean removeVendor(long vendorId) throws Exception;
	
	boolean updateVendor(Vendor vendor) throws Exception;
}
