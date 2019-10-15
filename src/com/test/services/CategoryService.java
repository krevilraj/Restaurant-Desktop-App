package com.test.services;

import java.util.HashMap;
import java.util.List;

import com.test.models.CategoryModel;



public interface CategoryService {

	/* Add Category */
		public boolean addCategory(CategoryModel s);
		
	/* Get List of Category */
		public List<CategoryModel> getCategory();
		
	/* Delete the Category of specific id */
		public boolean deleteCategory(int id);
		
	/* Update the Category */
		public boolean updateCategory(CategoryModel s);
		
	/* Get the Category Detail to update */
		public CategoryModel getById(int id);
		
	/* To populate key value pair for jcombobox */
		public HashMap<String, Integer> populateCategory();
		
	
}
