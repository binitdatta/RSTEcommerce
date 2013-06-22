package com.rollingstone.customer.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rollingstone.customer.model.ProductCategory;
import com.rollingstone.customer.service.IProductCategoryService;

@Controller
public class ProductCategoryController {
	Logger logger = Logger.getLogger(ProductCategoryController.class);
	
	private IProductCategoryService productCategoryService;

	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public IProductCategoryService getProductCategoryService() {
		return productCategoryService;
	}

	@Autowired
	@Required
	public void setProductCategoryService(
			IProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}

	
	
	@RequestMapping(value="/productCategory/add.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public boolean addProductCategory(@RequestBody ProductCategory productCategory) throws Exception {
		 productCategoryService.addProductCategory(productCategory);
		 
		 return true;
	}

	@RequestMapping(value = { "/productCatrgory/remove.do", "/productCatrgory/delete.do" }, method = RequestMethod.GET)
	@ResponseBody
	public boolean removeProductrCategoryr(@RequestParam("productCategoryId") long productCategoryId)  throws Exception  {
		System.out.println("Inside removeCustomer");
		productCategoryService.removeProductCategory(productCategoryId);
		return true;
	}
	
	@RequestMapping(value = "/productCategory/update.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateProductCategory(@RequestBody ProductCategory productCategory)  throws Exception  {
		productCategoryService.updateProductCategory(productCategory);
		return true;
	}

	@RequestMapping(value="/productCatrgory/list.view", method = RequestMethod.GET)
	public @ResponseBody List<ProductCategory> listProducCatrgoies() {
		List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
		
        return productCategories;
	}
}
