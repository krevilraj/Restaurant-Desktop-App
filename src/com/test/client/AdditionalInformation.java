package com.test.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.Cursor;
import javax.swing.JComboBox;

import com.test.helper.Encryption;
import com.test.models.UserModel;
import com.test.serviceimpl.Auth;
import com.test.services.AuthService;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdditionalInformation extends JFrame {

	private JPanel contentPane;
	private JTextField txt_phone;
	private JTextField txt_tax_file_number;
	private JTextField txt_address;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdditionalInformation frame = new AdditionalInformation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdditionalInformation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 784, 483);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 102, 204));
		panel_1.setBounds(0, 0, 395, 483);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AdditionalInformation.class.getResource("/image/bg_forget.jpg")));
		lblNewLabel.setBounds(0, 0, 395, 483);
		panel_1.add(lblNewLabel);
		
		JLabel lblCreateNewUser = new JLabel("Create new User Account  Sign Up");
		lblCreateNewUser.setBounds(58, 444, 317, 28);
		panel_1.add(lblCreateNewUser);
		lblCreateNewUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCreateNewUser.setForeground(new Color(57,145,230));
		lblCreateNewUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateNewUser.setFont(new Font("Calibri", Font.BOLD, 12));
		
		JLabel lblSignUp = new JLabel("Additional Information");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setForeground(new Color(102, 102, 102));
		lblSignUp.setFont(new Font("Calibri", Font.PLAIN, 36));
		lblSignUp.setBounds(431, 54, 333, 52);
		panel.add(lblSignUp);
		
		JLabel lblUsername = new JLabel("Phone");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblUsername.setBounds(431, 202, 155, 28);
		panel.add(lblUsername);
		
		txt_phone = new JTextField();
		txt_phone.setFont(new Font("Calibri", Font.PLAIN, 16));
		txt_phone.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txt_phone.setBounds(431, 241, 317, 43);
		panel.add(txt_phone);
		txt_phone.setColumns(10);
		
		JLabel lblPassword = new JLabel("Address");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblPassword.setBounds(431, 117, 155, 20);
		panel.add(lblPassword);
		
		JButton btn_register = new JButton("Register");
		btn_register.setBorder(null);
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phone = txt_phone.getText();
				String address = txt_address.getText();
				String tax_file_number = txt_tax_file_number.getText();
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				
				
				if(validation()) {
					String date_of_birth = df.format(dateChooser.getDate());
					AuthService ls=new Auth();
					UserModel user = new UserModel();
					
					user.setPhone(phone);
					user.setAddress(address);
					user.setTax_file_number(tax_file_number);
					user.setDate_of_birth(date_of_birth);					
					
					if(ls.updateUser(user)){
						JOptionPane.showMessageDialog(null, "Success!!! now you can login");
						new Login().setVisible(true);
						dispose();						
						
					}else{
						JOptionPane.showMessageDialog(null, "Login failed");
					}
					
						
						
					
				}
				
			}
		});
		btn_register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_register.setForeground(Color.WHITE);
		btn_register.setFont(new Font("Calibri", Font.PLAIN, 16));
		btn_register.setBackground(new Color(0, 102, 204));
		btn_register.setBounds(493, 429, 167, 43);
		panel.add(btn_register);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(57,145,230));
		panel_2.setBounds(745, 11, 29, 28);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblX = new JLabel("X");
		lblX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblX.setForeground(Color.WHITE);
		lblX.setBounds(0, 0, 29, 28);
		lblX.setFont(new Font("Calibri", Font.BOLD, 12));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblX);
		
		JLabel lblTaxFileNumber = new JLabel("Tax File Number");
		lblTaxFileNumber.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTaxFileNumber.setBounds(431, 295, 155, 20);
		panel.add(lblTaxFileNumber);
		
		txt_tax_file_number = new JTextField();
		txt_tax_file_number.setBounds(431, 326, 145, 43);
		panel.add(txt_tax_file_number);
		
		JLabel lblConfirmPassword = new JLabel("Date of Birth");
		lblConfirmPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(588, 295, 155, 20);
		panel.add(lblConfirmPassword);
		
		txt_address = new JTextField();
		txt_address.setFont(new Font("Calibri", Font.PLAIN, 16));
		txt_address.setColumns(10);
		txt_address.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txt_address.setBounds(431, 148, 317, 43);
		panel.add(txt_address);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(588, 326, 160, 43);
		dateChooser.setEnabled(false);
		dateChooser.getCalendarButton().setEnabled(true);
		panel.add(dateChooser);
		setUndecorated(true);
	}

	protected boolean validation() {
		
		
		if(txt_address.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Must write Address");
			return false;
		}	
		if(txt_phone.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Must write Phone");
			return false;
		}
		try {
			int product_price = Integer.parseInt(txt_phone.getText());

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Write number in Phone");
			return false;
		}
		if(txt_tax_file_number.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Must write Tax file Number");
			return false;
		}	
		if(this.dateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(rootPane, "Please choose date");
			return false;
		} 
		return true;
	}
}
