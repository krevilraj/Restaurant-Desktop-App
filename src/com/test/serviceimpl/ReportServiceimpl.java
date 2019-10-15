package com.test.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.DefaultCategoryDataset;

import com.test.db.DBConnection;
import com.test.models.CategoryModel;
import com.test.models.ItemCountModel;
import com.test.models.ItemEarnModel;
import com.test.services.CategoryService;
import com.test.services.ReportService;

public class ReportServiceimpl implements ReportService {

	Connection con = null;

	public ReportServiceimpl() {
		con = DBConnection.getDBConnection();
	}

	
	
	@Override
	public DefaultCategoryDataset getGraphItemSoldToday() {
		String sql = "SELECT product_name,count(`price`) as count FROM product_orders WHERE `updated_date` = CURDATE() GROUP BY `product_name` ORDER BY count DESC limit 10";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			

			while (rs.next()) {
				dataset.setValue(rs.getInt("count"), "Count", rs.getString("product_name"));				
			}
			return dataset;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<ItemCountModel> getTableItemSoldToday() {
		List<ItemCountModel> sList = new ArrayList<ItemCountModel>();
		String sql = "SELECT product_name,count(`price`) as count FROM product_orders WHERE `updated_date` = CURDATE() GROUP BY `product_name` ORDER BY count DESC";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				ItemCountModel s = new ItemCountModel();				
				s.setProduct_name(rs.getString("product_name"));
				s.setCount(rs.getInt("count"));
				sList.add(s);
			}
			return sList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sList;
	}


	@Override
	public DefaultCategoryDataset getGraphItemEarnToday() {
		String sql = "SELECT *,sum(`price`) as total FROM product_orders WHERE `updated_date` = CURDATE() GROUP BY `product_name` ORDER BY total DESC limit 10";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			

			while (rs.next()) {
				dataset.setValue(rs.getInt("total"), "Total", rs.getString("product_name"));				
			}
			return dataset;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<ItemEarnModel> getTableItemEarnToday() {
		List<ItemEarnModel> sList = new ArrayList<ItemEarnModel>();
		String sql = "SELECT *,sum(`price`) as total FROM product_orders WHERE `updated_date` = CURDATE() GROUP BY `product_name` ORDER BY total DESC";

		try {
			Statement stm = con.createStatement();			
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				ItemEarnModel s = new ItemEarnModel();				
				s.setProduct_name(rs.getString("product_name"));
				s.setPrice(rs.getDouble("total"));
				sList.add(s);
			}
			return sList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sList;
	}	

	
	
	public DefaultCategoryDataset getGraphItemSoldYesterday() {
		String sql = "select *,count(`id`) as count from product_orders where DATE(`updated_date`) = DATE(NOW() - INTERVAL 1 DAY) GROUP BY `product_name` ORDER BY count DESC limit 10";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			

			while (rs.next()) {
				dataset.setValue(rs.getInt("count"), "Count", rs.getString("product_name"));				
			}
			return dataset;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<ItemCountModel> getTableItemSoldYesterday() {
		List<ItemCountModel> sList = new ArrayList<ItemCountModel>();
		String sql = "select *,count(`id`) as count from product_orders where DATE(`updated_date`) = DATE(NOW() - INTERVAL 1 DAY) GROUP BY `product_name` ORDER BY count DESC";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				ItemCountModel s = new ItemCountModel();				
				s.setProduct_name(rs.getString("product_name"));
				s.setCount(rs.getInt("count"));
				sList.add(s);
			}
			return sList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sList;
	}


	@Override
	public DefaultCategoryDataset getGraphItemEarnYesterday() {
		String sql = "SELECT *,sum(`price`) as total FROM product_orders WHERE DATE(`updated_date`) = DATE(NOW() - INTERVAL 1 DAY)  GROUP BY `product_name` ORDER BY total DESC limit 10";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			

			while (rs.next()) {
				dataset.setValue(rs.getInt("total"), "Total", rs.getString("product_name"));				
			}
			return dataset;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<ItemEarnModel> getTableItemEarnYesterday() {
		List<ItemEarnModel> sList = new ArrayList<ItemEarnModel>();
		String sql = "SELECT *,sum(`price`) as total FROM product_orders WHERE DATE(`updated_date`) = DATE(NOW() - INTERVAL 1 DAY)  GROUP BY `product_name` ORDER BY total DESC";

		try {
			Statement stm = con.createStatement();			
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				ItemEarnModel s = new ItemEarnModel();				
				s.setProduct_name(rs.getString("product_name"));
				s.setPrice(rs.getDouble("total"));
				sList.add(s);
			}
			return sList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sList;
	}



	@Override
	public DefaultCategoryDataset getCustomSoldGraph(String start, String end) {
		String sql = "SELECT product_name,count(`price`) as count FROM product_orders WHERE `updated_date` BETWEEN '"+start+"' AND '"+end+"' GROUP BY `product_name` ORDER BY count DESC limit 10";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			

			while (rs.next()) {
				dataset.setValue(rs.getInt("count"), "Count", rs.getString("product_name"));				
			}
			return dataset;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}



	@Override
	public List<ItemCountModel> getCustomSoldTable(String start, String end) {
		List<ItemCountModel> sList = new ArrayList<ItemCountModel>();
		String sql = "SELECT product_name,count(`price`) as count FROM product_orders WHERE `updated_date` BETWEEN '"+start+"' AND '"+end+"' GROUP BY `product_name` ORDER BY count DESC";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				ItemCountModel s = new ItemCountModel();				
				s.setProduct_name(rs.getString("product_name"));
				s.setCount(rs.getInt("count"));
				sList.add(s);
			}
			return sList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sList;
	}



	@Override
	public DefaultCategoryDataset getCustomEarnGraph(String start, String end) {
		String sql = "SELECT *,sum(`price`) as total FROM product_orders WHERE `updated_date` BETWEEN '"+start+"' AND '"+end+"' GROUP BY `product_name` ORDER BY total DESC limit 10";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			

			while (rs.next()) {
				dataset.setValue(rs.getInt("total"), "Total", rs.getString("product_name"));				
			}
			return dataset;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}



	@Override
	public List<ItemEarnModel> getCustomEarnTable(String start, String end) {
		List<ItemEarnModel> sList = new ArrayList<ItemEarnModel>();
		String sql = "SELECT *,sum(`price`) as total FROM product_orders WHERE `updated_date` BETWEEN '"+start+"' AND '"+end+"' GROUP BY `product_name` ORDER BY total DESC";

		try {
			Statement stm = con.createStatement();			
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				ItemEarnModel s = new ItemEarnModel();				
				s.setProduct_name(rs.getString("product_name"));
				s.setPrice(rs.getDouble("total"));
				sList.add(s);
			}
			return sList;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sList;
	}



	
}
