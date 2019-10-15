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
import com.test.models.ProductOrderModel;
import com.test.services.ProductOrderService;
import com.test.services.ProductService;
import com.test.services.ProductOrderService;

public class ProductOrderServiceimpl implements ProductOrderService{

	Connection con = null;

	public ProductOrderServiceimpl() {
		con = DBConnection.getDBConnection();
	}	

	
	@Override
	public boolean addProductOrder(ProductOrderModel s) {
		String sql = "insert into product_orders (`order_id`, `product_name`, `quantity`, `deliver_type`, `price`,`updated_date`) values(?,?,?,?,?,?)";

		try {
			PreparedStatement pstm = con.prepareStatement(sql);

			
			pstm.setDouble(1, s.getOrder_id());
			pstm.setString(2, s.getProduct_name());	
			pstm.setInt(3, s.getQuantity());
			pstm.setString(4, s.getDeliver_type());
			pstm.setDouble(5, s.getPrice());
			pstm.setString(6, s.getUpdated_date());
			pstm.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	
	@Override
	public List<ProductOrderModel> getProductOrder(int order_id) {
		
		List<ProductOrderModel> sList = new ArrayList<ProductOrderModel>();

		String sql = "select * from product_orders where order_id = '"+order_id+"'";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				ProductOrderModel s = new ProductOrderModel();
				s.setId(rs.getInt("id"));
				s.setOrder_id(rs.getInt("order_id"));
				s.setProduct_name(rs.getString("product_name"));
				s.setQuantity(rs.getInt("quantity"));
				s.setDeliver_type(rs.getString("deliver_type"));
				s.setPrice(rs.getDouble("price"));	
				s.setUpdated_date(rs.getString("updated_date"));
				sList.add(s);
			}
			return sList;   //return the list of product of order_id given
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sList;
	}

	

	@Override
	public boolean deleteProductOrder(int id) {
		String sql = "delete from product_orders where id = '" + id + "'";
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
	public boolean updateProductOrder(ProductOrderModel s) {
		String sql = "update product_orders set order_id = ?,product_name = ?,quantity = ?,deliver_type = ?,price = ?,updated_date=? where id = ?";

		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, s.getOrder_id());
			pstm.setString(2, s.getProduct_name());
			pstm.setInt(3, s.getQuantity());
			pstm.setString(4, s.getDeliver_type());	
			pstm.setDouble(5, s.getPrice());
			pstm.setString(6, s.getUpdated_date());
			pstm.setInt(7, s.getId());
			pstm.execute();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public ProductOrderModel getById(int id) {
		String sql = "select * from product_orders where id = '" + id + "'";
		ProductOrderModel s = new ProductOrderModel();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				s.setId(rs.getInt("id"));
				s.setOrder_id(rs.getInt("order_id"));
				s.setProduct_name(rs.getString("product_name"));
				s.setQuantity(rs.getInt("quantity"));
				s.setDeliver_type(rs.getString("deliver_type"));
				s.setPrice(rs.getDouble("price"));	
				s.setUpdated_date(rs.getString("updated_date"));
				
			}
			return s;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return s;
	}










	@Override
	public List<ProductModel> getMainOrder() {
		List<ProductModel> sList = new ArrayList<ProductModel>();

		String sql = "select * from products where category_id = 1";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				ProductModel s = new ProductModel();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setCategory_id(rs.getInt("category_id"));
				s.setDescription(rs.getString("description"));
				s.setPrice(rs.getDouble("price"));
				s.setSize(rs.getString("size"));
				s.setStatus(rs.getString("status"));
				sList.add(s);
			}
			return sList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sList;
	}

	
	

}
