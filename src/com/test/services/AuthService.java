package com.test.services;


import java.util.List;

import com.test.models.UserModel;

public interface AuthService {
	/* the service that AuthServie provide */
	
	/* User Can login to verify user type i.e Admin, Manager, SalesPerson */
	public UserModel login(String username, String password);

	/* User can be added by Admin */
	public boolean addUser(UserModel user);
	
	/* User is checked whether it is in database or not for forget Password */
	public boolean userCheck(String username, String email);

	/* User can reset his password */
	public boolean resetPassword(String username, String email,String password);
	
	/* Admin can update the user info */
	public boolean updateUser(UserModel user);
	
	/* Admin can see user Information */
	public UserModel getById(int id);
	
	/* Get all the user List */
	public List<UserModel> getUser();
	
	/* this all method will inherit by the serviceImentation method i.e Auth.jav */
}
