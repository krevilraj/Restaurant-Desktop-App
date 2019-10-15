package com.test.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.db.DBConnection;
import com.test.models.UserModel;
import com.test.services.UserService;

public class UserServiceimpl implements UserService {

	Connection con = null;

	public UserServiceimpl() {
		con = DBConnection.getDBConnection();
	}

	@Override
	public boolean addUser(UserModel c) {

		String sql = "insert into users (username,firstname,lastname,email,password,date_of_birth,phone,tax_file_number,address,user_type) values(?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setString(1, c.getUsername());
			pstm.setString(2, c.getFirstname());
			pstm.setString(3, c.getLastname());
			pstm.setString(4, c.getEmail());
			pstm.setString(5, c.getPassword());
			pstm.setString(6, c.getDate_of_birth());
			pstm.setString(7, c.getPhone());
			pstm.setString(8, c.getTax_file_number());
			pstm.setString(9, c.getAddress());
			pstm.setString(10, c.getUser_type());
			

			pstm.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return false;
	}

	
	
	/* 
	 * Return the list of the User or Employee 
	 * 
	 * */
	
	@Override
	public List<UserModel> getUser() {
		List<UserModel> sList = new ArrayList<UserModel>();   // initialize the list of user

		/* Query to get the all user */
		String sql = "select * from users";

		try {
			
			/* prepare the query */
			Statement stm = con.createStatement();
			/* Execute the Query */
			ResultSet rs = stm.executeQuery(sql);

			/* all the result is in rs so we loop the rs.next() til we get all the user */
			while (rs.next()) {
				
				UserModel s = new UserModel();
				/* Set the value to usermodel */
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setFirstname(rs.getString("firstname"));
				s.setLastname(rs.getString("lastname"));
				s.setEmail(rs.getString("email"));
				s.setDate_of_birth(rs.getString("date_of_birth"));
				s.setPhone(rs.getString("phone"));
				s.setTax_file_number(rs.getString("tax_file_number"));
				s.setAddress(rs.getString("address"));
				s.setUser_type(rs.getString("user_type"));
				
				/* Add the user to list */
				sList.add(s);
			}
			/* return all list of the user */
			return sList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sList;
	}

	@Override
	public boolean deleteUser(int id) {
		/* query to delte the user whose id is given */
		String sql = "delete from users where id = '" + id + "'";
		try {
			Statement stm = con.createStatement();
			stm.execute(sql);
			return true;				// return true if user is deleted 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;					// return false if user is not deleted
	}

	@Override
	public boolean updateUser(UserModel s) {
		String sql = "update users set username = ?,firstname = ?,lastname= ?,email = ?,date_of_birth = ?,tax_file_number= ?,phone = ?,address = ?,user_type = ? where id = ?";

		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, s.getUsername());
			pstm.setString(2, s.getFirstname());
			pstm.setString(3, s.getLastname());	
			pstm.setString(4, s.getEmail());
			pstm.setString(5, s.getDate_of_birth());
			pstm.setString(6, s.getTax_file_number());
			pstm.setString(7, s.getPhone());
			pstm.setString(8, s.getAddress());
			pstm.setString(9, s.getUser_type());
			pstm.setInt(10, s.getId());
			pstm.execute();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public UserModel getById(int id) {
		
		/* Query to get the detail of user */
		String sql = "select * from users where id = '" + id + "'";
		UserModel s = new UserModel();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				/* Set the value to the Usermodel */
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setFirstname(rs.getString("firstname"));
				s.setLastname(rs.getString("lastname"));
				s.setEmail(rs.getString("email"));
				s.setDate_of_birth(rs.getString("date_of_birth"));
				s.setPhone(rs.getString("phone"));
				s.setTax_file_number(rs.getString("tax_file_number"));
				s.setAddress(rs.getString("address"));
				s.setUser_type(rs.getString("user_type"));				
				
			}
			
			/* REturn the Usermodel s */
			return s;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return s;
	}

	

}
