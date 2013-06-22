package com.rollingstone.customer.service;

import java.util.List;

import com.rollingstone.customer.model.ProductCategory;

public interface IProductCategoryService {

	ProductCategory addProductCategory(ProductCategory productCategory) throws Exception;
	
	List<ProductCategory> getAllProductCategories();
	
	boolean removeProductCategory(long productCategoryId) throws Exception;
	
	boolean updateProductCategory(ProductCategory productCategory) throws Exception;
}
