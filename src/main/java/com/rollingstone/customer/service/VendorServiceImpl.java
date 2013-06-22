package com.rollingstone.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.rollingstone.customer.dao.IVendorDao;
import com.rollingstone.customer.model.Vendor;

@Service
public class VendorServiceImpl implements IVendorService{

	IVendorDao vendorDao;
	
	public IVendorDao getVendorDao() {
		return vendorDao;
	}

	@Autowired
	@Required
	public void setVendorDao(IVendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	

	@Override
	public Vendor addVendor(Vendor vendor) throws Exception {
	
		return vendorDao.addVendor(vendor);
	}

	@Override
	public List<Vendor> getAllProducts() {
		return vendorDao.getAllVendors();
	} 

	@Override
	public boolean removeVendor(int vendorId) throws Exception {
		return vendorDao.removeVendor(vendorId);
	}

	@Override
	public boolean updateVendor(Vendor vendor) throws Exception {
		return vendorDao.updateVendor(vendor);
	}
}
