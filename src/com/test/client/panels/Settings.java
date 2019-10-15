package com.test.client.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.test.models.ProductModel;
import com.test.models.SettingModel;
import com.test.serviceimpl.ProductServiceimpl;
import com.test.serviceimpl.SettingServiceimpl;
import com.test.services.ProductService;
import com.test.services.SettingService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Settings extends JPanel {
	private JTextField txt_gst;
	private JTextField txt_discount;

	/**
	 * Create the panel.
	 */
	public Settings() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Calibri", Font.BOLD, 32));
		lblSettings.setBounds(35, 33, 400, 48);
		add(lblSettings);
		
		JLabel lblSettingForOrder = new JLabel("Setting for Order");
		lblSettingForOrder.setFont(new Font("Calibri", Font.BOLD, 16));
		lblSettingForOrder.setBounds(35, 92, 165, 27);
		add(lblSettingForOrder);
		
		JLabel lblGst = new JLabel("GST");
		lblGst.setFont(new Font("Calibri", Font.BOLD, 14));
		lblGst.setBounds(58, 141, 84, 27);
		add(lblGst);
		
		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setFont(new Font("Calibri", Font.BOLD, 14));
		lblDiscount.setBounds(58, 179, 84, 27);
		add(lblDiscount);
		
		txt_gst = new JTextField();
		txt_gst.setBounds(143, 142, 170, 20);
		add(txt_gst);
		txt_gst.setColumns(10);
		
		txt_discount = new JTextField();
		txt_discount.setColumns(10);
		txt_discount.setBounds(143, 179, 170, 20);
		add(txt_discount);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBorder(null);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(settingValidation()) {  
					SettingService ls = new SettingServiceimpl();
					SettingModel setting = new SettingModel();
					setting.setGst(Double.parseDouble(txt_gst.getText()));
					setting.setDiscount(Double.parseDouble(txt_discount.getText()));
					if(ls.updateSetting(setting)) {
						JOptionPane.showMessageDialog(null, "Setting updated successfully!!!");
					}else {
						JOptionPane.showMessageDialog(null, "Something went wrong");
					}
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(new Color(0, 102, 204));
		btnUpdate.setBounds(207, 238, 106, 36);
		add(btnUpdate);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 204));
		panel.setBounds(35, 119, 149, 2);
		add(panel);
		setVisible(true);
		populateSetting();  // display setting in the field

	}

	private void populateSetting() {
		
		SettingService ls = new SettingServiceimpl();
		
		SettingModel setting = ls.getSetting();
		txt_gst.setText(setting.getGst()+"");
		txt_discount.setText(setting.getDiscount()+"");	
		
		
	}

	protected boolean settingValidation() {
		if (txt_gst.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write GST");
			return false;
		}
		if (txt_discount.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Discount");
			return false;
		}
		try {
			float gst = Float.parseFloat(txt_gst.getText());

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "GST is not in correct form");
			return false;
		}
		try {
			float discount = Float.parseFloat(txt_discount.getText());

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Discount is not a number");
			return false;
		}
		return true;		
	}
}
