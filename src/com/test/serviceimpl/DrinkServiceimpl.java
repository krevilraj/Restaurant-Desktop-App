package com.test.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.db.DBConnection;
import com.test.models.ProductModel;
import com.test.services.DrinkService;
import com.test.services.ProductService;

public class DrinkServiceimpl implements DrinkService {

	/*
	 * Initialize the connection so that to check if the connection is null or not
	 * then take connection from the DBConnection Class
	 */
	Connection con = null;

	public DrinkServiceimpl() {
		/* get the connection to the database */
		con = DBConnection.getDBConnection();				// calling the static function of DBConnection Class getDBConnection() method
	}
	

	
	public boolean addDrink(ProductModel s) {
		/* Query to insert into products table */
		String sql = "insert into products (`category_id`, `name`, `description`, `size`, `price`, `status`) values(?,?,?,?,?,?)";

		try {
			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setInt(1, 2);						// replace ? of the query to 2 becuase 2 is id of drink in category table
			pstm.setString(2, s.getName());			//	replace ? with name
			pstm.setString(3, s.getDescription());	// replace ? with description	
			pstm.setString(4, s.getSize());			// replace ? with size
			pstm.setDouble(5, s.getPrice());		// replace ? with price
			pstm.setString(6, s.getStatus());		// replace ? with status
			/* Execute the query */
			pstm.execute();		
			/* REturn true if all goes right */
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public List<ProductModel> getDrink() {
		/* initialze the list of ProductModel */
		List<ProductModel> sList = new ArrayList<ProductModel>();

		/* Query to select all the drinks from the products table */
		String sql = "select * from products where category_id = 2";

		try {
			/* prepare Statement and execute the above query */
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			/* Loop till we get all the data fromt the table */
			while (rs.next()) {
				ProductModel s = new ProductModel();
				s.setId(rs.getInt("id"));						// set the id with the value return from the table
				s.setName(rs.getString("name"));				// set the name with the value return from the table
				s.setCategory_id(rs.getInt("category_id"));		// set the category id with the value return from the table
				s.setDescription(rs.getString("description"));	// set the description with the value return from the table
				s.setPrice(rs.getDouble("price"));				// set the price with the value return from the table
				s.setSize(rs.getString("size"));				// set the size with the value return from the table	
				s.setStatus(rs.getString("status"));			// set the status with the value return from the table
				sList.add(s);
			}
			/* Return the list of Product */
			return sList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sList;
	}


	public boolean deleteDrink(int id) {
		/* Query to delete the product whose id is given */
		String sql = "delete from products where id = '" + id + "'";
		try {
			/* prepare the query to execute */
			Statement stm = con.createStatement();
			/* Execute the query */
			stm.execute(sql);
			/* REturn true if all goes right */
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateDrink(ProductModel s) {
		/* Query to update the product table whose id is given */
		String sql = "update products set category_id = ?,name = ?,description = ?,size = ?,price = ?,status= ? where id = ?";

		try {
			/* prepare the query */
			PreparedStatement pstm = con.prepareStatement(sql);
			
//			pstm.setInt(1, 2);							// replace 1st ? of the query with 2
			pstm.setString(2, s.getName());				// replace 2nd ? of the query with name
			pstm.setString(3, s.getDescription());		// replace 3RD ? of the query with description
			pstm.setString(4, s.getSize());				// replace 4th ? of the query with size
			pstm.setDouble(5, s.getPrice());			// replace 5th ? of the query with price
			pstm.setString(6, s.getStatus());			// replace 6th ? of the query with status
			pstm.setInt(7, s.getId());					// replace 7th ? of the query with id
			pstm.execute();

			/* Return true if all goes right */
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public ProductModel getById(int id) {
		/* query to get the detail of the product whose id is given */
		String sql = "select * from products where id = '" + id + "'";
		ProductModel s = new ProductModel();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			/* Loop till we get all the date from the table */
			while (rs.next()) {
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setCategory_id(rs.getInt("category_id"));
				s.setDescription(rs.getString("description"));
				s.setPrice(rs.getDouble("price"));
				s.setSize(rs.getString("size"));
				s.setStatus(rs.getString("status"));				
			}
			return s;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return s;
	}

	

}
