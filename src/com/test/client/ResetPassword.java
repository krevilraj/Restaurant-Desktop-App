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

import com.test.helper.Encryption;
import com.test.serviceimpl.Auth;
import com.test.services.AuthService;

import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class ResetPassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField txt_password2;
	private JPasswordField txt_password;
	private String username="",email ="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPassword frame = new ResetPassword();
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
	public ResetPassword() {
		
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
		lblNewLabel.setIcon(new ImageIcon(ResetPassword.class.getResource("/image/bg_forget.jpg")));
		lblNewLabel.setBounds(0, 0, 395, 483);
		panel_1.add(lblNewLabel);
		
		JLabel lblSignUp = new JLabel("Change Password");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setForeground(new Color(102, 102, 102));
		lblSignUp.setFont(new Font("Calibri", Font.PLAIN, 36));
		lblSignUp.setBounds(431, 45, 317, 64);
		panel.add(lblSignUp);
		
		JLabel lblUsername = new JLabel("New Password");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblUsername.setBounds(431, 120, 155, 28);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Confirm Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblPassword.setBounds(431, 224, 155, 20);
		panel.add(lblPassword);
		
		txt_password2 = new JPasswordField();
		txt_password2.setBounds(431, 255, 317, 43);
		panel.add(txt_password2);
		
		JButton btn_confirm = new JButton("Confirm");
		btn_confirm.setBorder(null);
		btn_confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(validation()) {
					String password = txt_password.getText();
					password = Encryption.MD5(password);
					AuthService ls = new Auth();
					if(ls.resetPassword(username,email,password)) {
						JOptionPane.showMessageDialog(null, "Success !!! Now you can login with new password");
						new Login().setVisible(true);
						dispose();
					}
				}
			}
		});
		btn_confirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_confirm.setForeground(Color.WHITE);
		btn_confirm.setFont(new Font("Calibri", Font.PLAIN, 16));
		btn_confirm.setBackground(new Color(0, 102, 204));
		btn_confirm.setBounds(505, 340, 167, 43);
		panel.add(btn_confirm);
		
		JLabel lblCreateNewUser = new JLabel("Back to Login");
		lblCreateNewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		lblCreateNewUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCreateNewUser.setForeground(new Color(57,145,230));
		lblCreateNewUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreateNewUser.setFont(new Font("Calibri", Font.BOLD, 12));
		lblCreateNewUser.setBounds(538, 405, 192, 28);
		panel.add(lblCreateNewUser);
		
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
		
		txt_password = new JPasswordField();
		txt_password.setBounds(431, 159, 317, 43);
		panel.add(txt_password);
		setUndecorated(true);
		setVisible(true);
	}

	protected boolean validation() {
		
		if(txt_password.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Password");
			return false;
		}	
		if(txt_password2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Confirm Password");
			return false;
		}
		if(!txt_password.getText().equals(txt_password2.getText())) {
			JOptionPane.showMessageDialog(null, "Confirm Password must be same");
			return false;
		}	
		return true;
	}
	
	public void resetPassword(String username,String email) {
		this.username = username;
		this.email = email;
	}
}
