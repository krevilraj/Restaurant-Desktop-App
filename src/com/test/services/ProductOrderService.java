package com.test.services;

import java.util.HashMap;
import java.util.List;

import com.test.models.ProductModel;
import com.test.models.ProductOrderModel;



public interface ProductOrderService {

		public boolean addProductOrder(ProductOrderModel s);
		public List<ProductOrderModel> getProductOrder(int id);
		public boolean deleteProductOrder(int id);
		public boolean updateProductOrder(ProductOrderModel s);
		public ProductOrderModel getById(int id);
		public List<ProductModel> getMainOrder();
	
}
