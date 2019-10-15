package com.test.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

import com.test.db.DBConnection;
import com.test.helper.Encryption;
import com.test.models.UserModel;
import com.test.services.AuthService;




public class Auth implements AuthService {
	/* it should implement all method that is defined in AuthService */
	/* initialize the connection to null so that if null then get connection */
	Connection con = null;
	
	 public Auth() {	
		/* get the connection to the database */
		con = DBConnection.getDBConnection();  			// calling the static function of DBConnection Class getDBConnection() method
	}

	@Override
	public UserModel login(String username,String password) {
		/* All the logic and the calculation to login*/
		/* password is encrypted to md5 */
		password = Encryption.MD5(password);     // we call the method of MD5 of the Encryption Class that is static
		
		// query to select or get the user whose username and password is given
		String sql = "select * from users where username = '"+username+"' and password = '"+password+"'";
		//Student s = new Student();	
				try {
			/* prepare the query to execute */
					Statement stm = con.createStatement();
					ResultSet rs = stm.executeQuery(sql);
					
			/* loop till we get the all the result after running the query */
					if(rs.next()){
						
				/* if we get the value then set it to the model ie UserModel */
						
				        UserModel user = new UserModel();
				        
				/* Place the value from the database data to model */
				        user.setFirstname(rs.getString("firstname"));     // set firstname in the UserModel
				        user.setLastname(rs.getString("lastname"));		// set lastname in the UserModel
				        user.setUser_type(rs.getString("user_type"));	
				        user.setAddress(rs.getString("address"));
				        user.setDate_of_birth(rs.getString("date_of_birth"));
				        user.setEmail(rs.getString("email"));
				        
				        
				/*REturn the user*/
						return user;
						
					}else{
						
						return null;
					}
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "something wrong in validation"+e);
					e.printStackTrace();
				}
				return null;
	}

	@Override
	public boolean addUser(UserModel c) {
		/* all the logic and calculation to add the user */
		/* Query to insert into users table */
		String sql = "insert into users (username,firstname,lastname,email,password,date_of_birth,phone,tax_file_number,address,user_type) values(?,?,?,?,?,?,?,?,?,?)";

		try {
			/* Prepare the query above query*/
			PreparedStatement pstm = con.prepareStatement(sql);

			/* Value in the above query put the value in ?,?,?,?,?,? . . .  respectively*/
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
			
			/* Execute the query */
			pstm.execute();
			/* If all ok then return true */
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* or else false at last */
			return false;
	}

	@Override
	public boolean userCheck(String username, String email) {
		/* Query to check user exist or not  if yes then return true or false*/
		String sql = "select * from users where username = '"+username+"' and email = '"+email+"'";	
				try {
			/* Create Statement for query */
					Statement stm = con.createStatement();
					
			/* Execute Query */
					ResultSet rs = stm.executeQuery(sql);
					
					
			/* If user exist return true else false */
					if(rs.next()){				        
						return true;
						
					}else{
						
						return false;
					}
					
				} catch (SQLException e) {
			/* If Exception like error in query or database connection error or error related to sql occur then this function is call */
					JOptionPane.showMessageDialog(null, "something wrong in validation"+e);
					e.printStackTrace();
				}
				return false;
	}

	@Override
	public boolean updateUser(UserModel s) {
		/*
		 * Get the last id from the user table to edit the additional information during
		 * register
		 */
		String sql = "SELECT MAX(id) as id FROM `users`";    // query to get the last id of user
		int id = -1; // initialize id as  -1 so that after the query if id doesn't change the user is not inserted in the table
		
		try {
			
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			/* If the query is success then id will be changed */
			if(rs.next()){
				id = rs.getInt("id");
				
			}
			
		} catch (SQLException e) {
			/* If the Exception related to sql query then This function is called */
			JOptionPane.showMessageDialog(null, "something wrong in validation"+e);
			e.printStackTrace();
		}
		
		
		/* To update the User Additional Information */
		if(id != -1) {
			 sql = "update users set address = ?,phone= ?,date_of_birth=?,tax_file_number= ? where id = ?";
			 try {
					PreparedStatement pstm = con.prepareStatement(sql);
					
				/*
				 * put the value of address = ?, phone = ?, date_of_birth=?,tax_file_number= ? where id = ?"; 
				 * respectively
				 */
					pstm.setString(1, s.getAddress()); //put value in address = 
					pstm.setString(2, s.getPhone());	//put value in phone = 
					pstm.setString(3, s.getDate_of_birth()); // put the value in dateof birth
					pstm.setString(4, s.getTax_file_number()); // put the value in taxfile number
					pstm.setInt(5, id);					// put the value in id
					pstm.execute();
					
				/* Return true if all things go right */
					return true;
				} catch (SQLException e) {
					// if Exception occur in Query
					e.printStackTrace();
				}

				
		}
		
		
		

		// return false if any thing is wrong else in above query it return true;
		return false;
	}

	@Override
	public UserModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserModel> getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean resetPassword(String username, String email, String password) {
		/* Query to reset the user password where the username and email matches */
		String sql = "update users set `password` = ? where username = ? and email = ?";
		 try {
				PreparedStatement pstm = con.prepareStatement(sql);

				pstm.setString(1, password);
				pstm.setString(2, username);
				pstm.setString(3, email);							
				pstm.execute();
				
				//return true if all things go right
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 // return false if any thing is wrong like exception or in query something like that
		return false;
	}
	
	
	
}
