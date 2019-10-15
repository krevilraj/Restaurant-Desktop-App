package com.test.services;

import java.util.List;

import com.test.models.ProductModel;



public interface ProductService {

		public boolean addProduct(ProductModel s);
		public List<ProductModel> getProduct();
		public boolean deleteProduct(int id);
		public boolean updateProduct(ProductModel s);
		public ProductModel getById(int id);
		public List<ProductModel> getProductByCat(int id);
		
	
}
