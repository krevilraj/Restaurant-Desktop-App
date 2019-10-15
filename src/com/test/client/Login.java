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
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;


import com.test.helper.Encryption;
import com.test.models.UserModel;
import com.test.serviceimpl.Auth;

import com.test.services.AuthService;

import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame implements KeyListener{

	private JPanel contentPane;
	private JTextField txt_username;
	private JPasswordField txt_password;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
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
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 395, 483);
		panel_1.add(label);
		label.setIcon(new ImageIcon(Login.class.getResource("/image/bg_login.jpg")));
		
		JLabel lblSignUp = new JLabel("Login");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setForeground(new Color(102, 102, 102));
		lblSignUp.setFont(new Font("Calibri", Font.PLAIN, 36));
		lblSignUp.setBounds(475, 45, 229, 64);
		panel.add(lblSignUp);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblUsername.setBounds(431, 120, 155, 28);
		panel.add(lblUsername);
		
		txt_username = new JTextField();
		txt_username.setFont(new Font("Calibri", Font.PLAIN, 16));
		txt_username.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txt_username.setBounds(431, 155, 317, 43);
		panel.add(txt_username);
		txt_username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblPassword.setBounds(431, 224, 155, 20);
		panel.add(lblPassword);
		
		txt_password = new JPasswordField();
		txt_password.setBounds(431, 255, 317, 43);
		panel.add(txt_password);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.setBorder(null);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginCheck();  // it will call the LoginCheck method
				
				
			}
		});
		btnLogIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogIn.setForeground(Color.WHITE);
		btnLogIn.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnLogIn.setBackground(new Color(0, 102, 204));
		btnLogIn.setBounds(505, 340, 167, 43);
		panel.add(btnLogIn);
		
		JLabel lblCreateNewUser = new JLabel("Create new User Account  Sign Up");
		lblCreateNewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Register().setVisible(true);
				dispose();
			}
		});
		lblCreateNewUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCreateNewUser.setForeground(new Color(57,145,230));
		lblCreateNewUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateNewUser.setFont(new Font("Calibri", Font.BOLD, 12));
		lblCreateNewUser.setBounds(431, 403, 317, 28);
		panel.add(lblCreateNewUser);
		
		JLabel lblForgetPassword = new JLabel("Forget Password");
		lblForgetPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ForgetPassword().setVisible(true);
				dispose();
			}
		});
		lblForgetPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblForgetPassword.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblForgetPassword.setForeground(new Color(57,145,230));
		lblForgetPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblForgetPassword.setBounds(593, 309, 155, 14);
		panel.add(lblForgetPassword);
		
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255,255,255));
		panel_3.setBounds(394, 0, 400, 483);
		panel.add(panel_3);
		panel_3.setLayout(null);
		setUndecorated(true);
		
		
		/* if the user press Enter then it will click login btn */
		txt_password.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		               btnLogIn.doClick();
		            }
				
			}
		});
		
		
	}
	protected void LoginCheck() {
		
		String username = txt_username.getText();					// get the value of username from the txt_username what the user type in that field
		String password = txt_password.getText();					// get the value of password from the txt_password what the user type in that field
		
		/*
		 * Check the validation if the user typed username and password or not else we
		 * give error message
		 */
		if(validation()) {											// validation method called
			
			/* if the validation return true */
			AuthService ls=new Auth();								// to get the service we initialize the service
			
			UserModel user = ls.login(username,password);			// use login service of the Auth service passing username and password
			if(user!=null){		
				/* if the user is not null then success message*/
				
				JOptionPane.showMessageDialog(null, "Success!!! Lets go to Dashboard");
				
				
				
				if(user.getUser_type().equals("Admin")) {  // if user is Admin then open Dashboard jFrame
					new Dashboard();
				}else if(user.getUser_type().equals("Manager")) {   // if user is Manager then open ManagerDashboard jFrame
					new ManagerDashboard();
				}else {												// // if user is SalesPerson then open SalesPersonDashboard jFrame
					new SalesPersonDashboard();
				}
				
				/*  close the Login Form */				
				dispose();
				
			}else{
				JOptionPane.showMessageDialog(null, "Login failed");
			}
		}
		
	}

	public boolean validation() {	
		
		if(txt_username.getText().equals("")) {							// if the txt_username value is empty
			JOptionPane.showMessageDialog(null, "Must write username");		// show the error message to user and return to above function i.e
			return false;
		}if(txt_password.getText().equals("")) {							// if the txt_password value is empty
			JOptionPane.showMessageDialog(null, "Must write password");			// show the error message to user and return to above function i.e
			return false;
		}			
		
		/* if all validation is correct then return true */
	return true;
}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
	       LoginCheck();
	    }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
