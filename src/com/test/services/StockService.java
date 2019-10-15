package com.test.services;

import java.util.HashMap;
import java.util.List;

import com.test.models.CategoryModel;
import com.test.models.StockModel;



public interface StockService {

	/* CRUD operation of Stock */
		public boolean addStock(StockModel s);
		public List<StockModel> getStock(String FoodType);
		public boolean deleteStock(int id);
		public boolean updateStock(StockModel s);
		public StockModel getById(int id);
			
	
}
