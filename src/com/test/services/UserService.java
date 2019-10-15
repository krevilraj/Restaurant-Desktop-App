package com.test.services;

import java.util.List;

import com.test.models.ProductModel;
import com.test.models.UserModel;



public interface UserService {
	
	/* CRUD operation of User */
		public boolean addUser(UserModel s);		// add the user or employee to table
		public List<UserModel> getUser();			// return the list of the User or Employee
		public boolean deleteUser(int id);			// delte the user or employee
		public boolean updateUser(UserModel s);		// update
		public UserModel getById(int id);			// get the detail of user or employee
		
	
}
