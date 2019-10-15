package com.test.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import com.test.db.DBConnection;
import com.test.models.ProductModel;
import com.test.models.OrderModel;
import com.test.services.OrderService;
import com.test.services.ProductService;
import com.test.services.OrderService;

public class OrderServiceimpl implements OrderService{

	Connection con = null;

	public OrderServiceimpl() {
		con = DBConnection.getDBConnection();
	}

	
	@Override
	public int addOrder(OrderModel s) {
		String sql = "insert into orders (`total_price`, `status`,`updated_date`,`payment_type`,`table_no`) values(?,?,?,?,?)";
		int id = -1;

		try {
			PreparedStatement pstm = con.prepareStatement(sql);

			
			pstm.setDouble(1, s.getTotal_price());
			pstm.setString(2, s.getStatus());
			pstm.setString(3, s.getDate());
			pstm.setString(4, s.getPaymentType());
			pstm.setInt(5, s.getTable_no());
			pstm.execute();
			
			String sql1 = "SELECT MAX(id) as id FROM `orders`";
			
			//Student s = new Student();	
			try {
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(sql1);
				
				if(rs.next()){
					id = rs.getInt("id");
					
				}
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "something wrong in validation"+e);
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}
	
	
	@Override
	public List<OrderModel> getOrder() {
		List<OrderModel> sList = new ArrayList<OrderModel>();

		String sql = "select * from orders ORDER BY id DESC";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				OrderModel s = new OrderModel();
				s.setId(rs.getInt("id"));
				s.setStatus(rs.getString("status"));
				s.setTotal_price(rs.getDouble("total_price"));	
				s.setDate(rs.getString("updated_date"));
				s.setPaymentType(rs.getString("payment_type"));
				s.setTable_no(rs.getInt("table_no"));
				sList.add(s);
			}
			return sList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sList;
	}

	

	@Override
	public boolean deleteOrder(int id) {
		String sql = "delete from orders where id = '" + id + "'";
		try {
			Statement stm = con.createStatement();
			stm.execute(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateOrder(OrderModel s) {
		String sql = "update orders set total_price = ?,status = ?,updated_date = ?,payment_type = ?,table_no = ? where id = ?";

		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setDouble(1, s.getTotal_price());
			pstm.setString(2, s.getStatus());
			pstm.setString(3, s.getDate());
			pstm.setString(4, s.getPaymentType());
			pstm.setInt(5, s.getTable_no());
			pstm.setInt(6, s.getId());
			pstm.execute();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public OrderModel getById(int id) {
		String sql = "select * from orders where id = '" + id + "'";
		
		OrderModel s = new OrderModel();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				s.setId(rs.getInt("id"));
				s.setStatus(rs.getString("status"));
				s.setTotal_price(rs.getDouble("total_price"));
				s.setDate(rs.getString("updated_date"));
				s.setPaymentType(rs.getString("payment_type"));
				s.setTable_no(rs.getInt("table_no"));
			}
			return s;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return s;
	}

	
	

}
