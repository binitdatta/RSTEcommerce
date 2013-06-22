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

import com.rollingstone.customer.model.Product;
import com.rollingstone.customer.service.IProductService;

@Controller
public class ProductController {
	Logger logger = Logger.getLogger(ProductController.class);
	
	private IProductService productService;

	public IProductService getProductService() {
		return productService;
	}

	@Autowired
	@Required
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value="/product/add.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public boolean addProduct(@RequestBody Product product) throws Exception {
		productService.addProduct(product);
		return true;
	}

	@RequestMapping(value = { "/product/remove.do", "/product/delete.do" }, method = RequestMethod.GET)
	@ResponseBody
	public boolean removeCustomer(@RequestParam("productId") long productId)  throws Exception  {
		System.out.println("Inside removeCustomer");
		productService.removeProduct(productId);
		return true;
	}
	
	@RequestMapping(value = "/product/update.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateProduct(@RequestBody Product product)  throws Exception  {
		productService.updateProduct(product);
		return true;
	}

	@RequestMapping(value="/product/list.view", method = RequestMethod.GET)
	public @ResponseBody List<Product> listProducts() {
		List<Product> products = productService.getAllProducts();
		
        return products;
	}
}
