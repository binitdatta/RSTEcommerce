package com.rollingstone.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.rollingstone.customer.dao.IProductDao;
import com.rollingstone.customer.model.Product;

@Service
public class ProductServiceImpl implements IProductService{

	IProductDao productDao;
	
	public IProductDao getProductDao() {
		return productDao;
	}

	@Autowired
	@Required
	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}

	

	@Override
	public Product addProduct(Product product) throws Exception {
	
		return productDao.addProduct(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public boolean removeProduct(long productId) throws Exception {
		return productDao.removeProduct(productId);
	}

	@Override
	public boolean updateProduct(Product product) throws Exception {
		return productDao.updateProduct(product);
	}
}
