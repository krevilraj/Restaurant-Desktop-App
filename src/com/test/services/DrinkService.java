package com.test.services;

import java.util.List;

import com.test.models.ProductModel;



public interface DrinkService {

		public boolean addDrink(ProductModel s);
		public List<ProductModel> getDrink();
		public boolean deleteDrink(int id);
		public boolean updateDrink(ProductModel s);
		public ProductModel getById(int id);		
	
}
