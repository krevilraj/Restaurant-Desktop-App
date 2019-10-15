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
import com.test.services.ProductService;

public class ProductServiceimpl implements ProductService {

	Connection con = null;

	public ProductServiceimpl() {
		con = DBConnection.getDBConnection();
	}

	@Override
	public boolean addProduct(ProductModel s) {

		String sql = "insert into products (`category_id`, `name`, `description`, `size`, `price`, `status`) values(?,?,?,?,?,?)";

		try {
			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setInt(1, s.getCategory_id());
			pstm.setString(2, s.getName());
			pstm.setString(3, s.getDescription());	
			pstm.setString(4, s.getSize());
			pstm.setDouble(5, s.getPrice());
			pstm.setString(6, s.getStatus());
			pstm.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<ProductModel> getProduct() {
		List<ProductModel> sList = new ArrayList<ProductModel>();

		String sql = "select * from products where category_id !=2";

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

	@Override
	public boolean deleteProduct(int id) {
		String sql = "delete from products where id = '" + id + "'";
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
	public boolean updateProduct(ProductModel s) {
		String sql = "update products set category_id = ?,name = ?,description = ?,size = ?,price = ?,status= ? where id = ?";

		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, s.getCategory_id());
			pstm.setString(2, s.getName());
			pstm.setString(3, s.getDescription());	
			pstm.setString(4, s.getSize());
			pstm.setDouble(5, s.getPrice());
			pstm.setString(6, s.getStatus());			
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
	public ProductModel getById(int id) {
		String sql = "select * from products where id = '" + id + "'";
		ProductModel s = new ProductModel();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

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

	@Override
	public List<ProductModel> getProductByCat(int id) {
		List<ProductModel> sList = new ArrayList<ProductModel>();

		String sql = "select * from products where category_id !=2 and category_id = '"+id+"'";

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
