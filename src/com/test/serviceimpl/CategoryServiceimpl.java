package com.test.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.db.DBConnection;
import com.test.models.CategoryModel;
import com.test.services.CategoryService;

public class CategoryServiceimpl implements CategoryService {

	/*
	 * Initialize the connection so that to check if the connection is null or not
	 * then take connection from the DBConnection Class
	 */
	Connection con = null;

	public CategoryServiceimpl() {
		/* get the connection to the database */
		con = DBConnection.getDBConnection();				// calling the static function of DBConnection Class getDBConnection() method
	}

	@Override
	public boolean addCategory(CategoryModel s) {
		
		/* Query to insert into categories Table */
		String sql = "insert into categories (category,status) values(?,?)";

		try {
			/* Prepare the statement to query */
			PreparedStatement pstm = con.prepareStatement(sql);

			
			pstm.setString(1, s.getName());     // put the value of name from the CategoryModel s
			pstm.setString(2, s.getStatus());			// put the value of status from the CategoryModel s
			pstm.execute();						// Execute the query
				
			return true;						// return true if inserted to the table

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false; 							// return false if it is not inserted to table
	}

	@Override
	public List<CategoryModel> getCategory() {
		/* Initialize the list type CategoryMOdel */
		List<CategoryModel> sList = new ArrayList<CategoryModel>();

		/* Query to get the list of id !=2 because id 2 is Drink Category and it is must it can't be deleted */
		String sql = "select * from categories where id != 2";

		try {
			/* prepare statement */
			Statement stm = con.createStatement();
			/* Execute the Query */
			ResultSet rs = stm.executeQuery(sql);

			/* Loop the data to list of CategoryMOdel then add to the sList*/
			while (rs.next()) {
				CategoryModel s = new CategoryModel();
				s.setId(rs.getInt("id"));					// add the id value in CategoryModel
				s.setName(rs.getString("category"));		// add the category name in Category Model
				s.setStatus(rs.getString("status"));		// add the status of in category model
				sList.add(s);								// then at last add the CategoryModel to the sList
			}
			/* Return the list to frontend */
			return sList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		/* Return the list null if the category is not in the table*/
		return sList;
	}

	@Override
	public boolean deleteCategory(int id) {
		/* Delete query in categories table where id = give value */
		String sql = "delete from categories where id = '" + id + "'";
		try {
			/* prepare statement for query */
			Statement stm = con.createStatement();
			/* Execute the query */
			stm.execute(sql);
			/* REturn true if query executeed */
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* If any thing wrong then return false; */
		return false;
	}

	@Override
	public boolean updateCategory(CategoryModel s) {
		/* Update Query to categories table */
		String sql = "update categories set category = ?,status= ? where id = ?";

		try {
			
			PreparedStatement pstm = con.prepareStatement(sql);				// prepare the query statement

			pstm.setString(1, s.getName());									// replace ? with name
			pstm.setString(2, s.getStatus());								// replace ? with status
			pstm.setInt(3, s.getId());										// replace ? with id
			pstm.execute();													// Execute the query

			/* if all goes right then return true */
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public CategoryModel getById(int id) {
		/*
		 * Query to get the detail of the category whose id is given (int id) from above
		 */
		String sql = "select * from categories where id = '" + id + "'";
		/* initialize the CategoryModel */
		CategoryModel s = new CategoryModel();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {

				s.setId(rs.getInt("id"));					// replace ? with id
				s.setName(rs.getString("category"));		// replace ? with category
				s.setStatus(rs.getString("status"));		// replace ? with status
			}
			return s;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return s;
	}

	@Override
	public HashMap<String, Integer> populateCategory() {
		/* initialize the HashMap(it stores value in key value pair) 
		 * Eg:
		 * 	pizza:1
		 * 	Drink:2
		 * 	burger:3
		 * 
		 * In above pizza, Drink and burger is key and 1 , 2 and 3 is value
		 * if we want id of pizza then we write map.get("pizza") then it give 1
		 * */
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		/* Query to get the detail of Category whose id !=2 */
		String sql = "select * from categories where id != 2";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			/* Loop until we get all the data from the query */
			while (rs.next()) {
				CategoryModel s = new CategoryModel();		//initialize the model
				s.setId(rs.getInt("id"));					// put the value to id of CategoryMOdel
				s.setName(rs.getString("category"));		// put the value to category of CategoryMOdel
				s.setStatus(rs.getString("status"));		// put the value to status of CategoryMOdel
				map.put(s.getName(),s.getId());
			}
			/* Return the hashmap value to frontend */
			return map;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		/* Else return null hashmap to frontend */
		return map;		
	}

	

}
