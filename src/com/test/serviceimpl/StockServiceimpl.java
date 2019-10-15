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
import com.test.models.ProductModel;
import com.test.models.StockModel;
import com.test.services.ProductService;
import com.test.services.StockService;

public class StockServiceimpl implements StockService {

	Connection con = null;

	public StockServiceimpl() {
		con = DBConnection.getDBConnection();
	}	

	
	@Override
	public boolean addStock(StockModel s) {
		String sql = "insert into stocks (`product_name`, `food_type`, `quantity`, `unit`) values(?,?,?,?)";

		try {
			PreparedStatement pstm = con.prepareStatement(sql);

			
			pstm.setString(1, s.getProduct_name());
			pstm.setString(2, s.getFood_type());	
			pstm.setInt(3, s.getQuantity());
			pstm.setString(4, s.getUnit());
			pstm.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	
	@Override
	public List<StockModel> getStock(String FoodType) {
		List<StockModel> sList = new ArrayList<StockModel>();

		String sql = "select * from stocks where food_type='"+FoodType+"'";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				StockModel s = new StockModel();
				s.setId(rs.getInt("id"));
				s.setProduct_name(rs.getString("product_name"));
				s.setFood_type(rs.getString("food_type"));
				s.setQuantity(rs.getInt("quantity"));
				s.setUnit(rs.getString("unit"));				
				sList.add(s);
			}
			return sList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sList;
	}

	

	@Override
	public boolean deleteStock(int id) {
		String sql = "delete from stocks where id = '" + id + "'";
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
	public boolean updateStock(StockModel s) {
		String sql = "update stocks set product_name = ?,quantity = ?,unit = ?,food_type = ? where id = ?";

		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, s.getProduct_name());
			pstm.setInt(2, s.getQuantity());
			pstm.setString(3, s.getUnit());	
			pstm.setString(4, s.getFood_type());			
			pstm.setInt(5, s.getId());
			pstm.execute();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public StockModel getById(int id) {
		String sql = "select * from stocks where id = '" + id + "'";
		StockModel s = new StockModel();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				s.setId(rs.getInt("id"));
				s.setProduct_name(rs.getString("product_name"));
				s.setFood_type(rs.getString("food_type"));
				s.setQuantity(rs.getInt("quantity"));
				s.setUnit(rs.getString("unit"));				
			}
			return s;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return s;
	}

	
	

}
