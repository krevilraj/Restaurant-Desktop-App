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
import com.test.models.SettingModel;
import com.test.services.CategoryService;
import com.test.services.SettingService;

public class SettingServiceimpl implements SettingService {

	Connection con = null;

	public SettingServiceimpl() {
		con = DBConnection.getDBConnection();
	}

	public boolean updateSetting(SettingModel s) {
		String sql = "update settings set gst = ?,discount= ? where id = ?";

		try {
			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setDouble(1, s.getGst());
			pstm.setDouble(2, s.getDiscount());			
			pstm.setInt(3, 1);
			pstm.execute();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public SettingModel getSetting() {
		String sql = "select * from settings where id = 1";
		SettingModel s = new SettingModel();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {

				s.setId(rs.getInt("id"));
				s.setGst(rs.getDouble("gst"));
				s.setDiscount(rs.getDouble("discount"));				
			}
			return s;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return s;
	}

	

	

}
