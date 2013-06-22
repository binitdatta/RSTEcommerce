package com.rollingstone.customer.dao;

import java.util.List;

import com.rollingstone.customer.model.ProductCategory;

public interface IProductCategoryDao {

	ProductCategory addProductCategory(ProductCategory category) throws Exception;
	
	List<ProductCategory> getAllProductCategories();
	
	boolean removeProductCategory(long productCategoryId) throws Exception;
	
	boolean updateProductCategory(ProductCategory productCategory) throws Exception;
}
