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

import com.test.serviceimpl.Auth;
import com.test.services.AuthService;

import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class ForgetPassword extends JFrame {

	private JPanel contentPane;
	private JTextField txt_username;
	private JTextField txt_email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetPassword frame = new ForgetPassword();
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
	public ForgetPassword() {
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
		lblNewLabel.setIcon(new ImageIcon(ForgetPassword.class.getResource("/image/bg_forget.jpg")));
		lblNewLabel.setBounds(0, 0, 395, 483);
		panel_1.add(lblNewLabel);
		
		JLabel lblSignUp = new JLabel("Confirmation");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setForeground(new Color(102, 102, 102));
		lblSignUp.setFont(new Font("Calibri", Font.PLAIN, 36));
		lblSignUp.setBounds(475, 45, 229, 64);
		panel.add(lblSignUp);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblUsername.setBounds(431, 130, 155, 28);
		panel.add(lblUsername);
		
		txt_username = new JTextField();
		txt_username.setFont(new Font("Calibri", Font.PLAIN, 16));
		txt_username.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txt_username.setBounds(431, 169, 317, 43);
		panel.add(txt_username);
		txt_username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Email Address");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblPassword.setBounds(431, 235, 155, 20);
		panel.add(lblPassword);
		
		JButton btn_proceed = new JButton("Proceed");
		btn_proceed.setBorder(null);
		btn_proceed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(validation()) {
					String username = txt_username.getText();
					String email = txt_email.getText();
					AuthService ls = new Auth();
					if(ls.userCheck(username, email)) {
						new ResetPassword().resetPassword(username, email);;
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "User is not registered");
					}
					
				}
			}
		});
		btn_proceed.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_proceed.setForeground(Color.WHITE);
		btn_proceed.setFont(new Font("Calibri", Font.PLAIN, 16));
		btn_proceed.setBackground(new Color(0, 102, 204));
		btn_proceed.setBounds(505, 363, 167, 43);
		panel.add(btn_proceed);
		
		JLabel lbl_login = new JLabel("Back to Login");
		lbl_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		lbl_login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_login.setForeground(new Color(57,145,230));
		lbl_login.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_login.setFont(new Font("Calibri", Font.BOLD, 12));
		lbl_login.setBounds(431, 429, 317, 28);
		panel.add(lbl_login);
		
		JLabel lblForgetPassword = new JLabel("Provide your account credential");
		lblForgetPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblForgetPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblForgetPassword.setForeground(new Color(51,51,51));
		lblForgetPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblForgetPassword.setBounds(429, 99, 243, 20);
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
		
		txt_email = new JTextField();
		txt_email.setFont(new Font("Calibri", Font.PLAIN, 16));
		txt_email.setColumns(10);
		txt_email.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txt_email.setBounds(431, 266, 317, 43);
		panel.add(txt_email);
		setUndecorated(true);
	}

	protected boolean validation() {
		if(txt_username.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Username");
			return false;
		}
		if(txt_email.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Email Address");
			return false;
		}	
		return true;
	}
}
