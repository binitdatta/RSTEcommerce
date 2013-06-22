package com.rollingstone.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.rollingstone.customer.dao.IProductCategoryDao;
import com.rollingstone.customer.model.ProductCategory;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService{

	IProductCategoryDao productCategoryDao;
	
	

	public IProductCategoryDao getProductCategoryDao() {
		return productCategoryDao;
	}


	@Autowired
	@Required
	public void setProductCategoryDao(IProductCategoryDao productCategoryDao) {
		this.productCategoryDao = productCategoryDao;
	}

	

	@Override
	public ProductCategory addProductCategory(ProductCategory productCategory) throws Exception {
	
		return productCategoryDao.addProductCategory(productCategory);
	}

	@Override
	public List<ProductCategory> getAllProductCategories() {
		return productCategoryDao.getAllProductCategories();
	}

	@Override
	public boolean removeProductCategory(long productCategoryId) throws Exception {
		return productCategoryDao.removeProductCategory(productCategoryId);
	}

	@Override
	public boolean updateProductCategory(ProductCategory product) throws Exception {
		return productCategoryDao.updateProductCategory(product);
	}
}
