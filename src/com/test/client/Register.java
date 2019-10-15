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
import java.awt.Component;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField txt_username;
	private JPasswordField txt_password;
	private JPasswordField txt_password2;
	private JTextField txt_email;
	private JTextField txt_firstname;
	private JTextField txt_lastname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
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
		lblNewLabel.setIcon(new ImageIcon(Register.class.getResource("/image/bg_register.jpg")));
		lblNewLabel.setBounds(0, 0, 395, 483);
		panel_1.add(lblNewLabel);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setForeground(new Color(102, 102, 102));
		lblSignUp.setFont(new Font("Calibri", Font.PLAIN, 36));
		lblSignUp.setBounds(472, 21, 229, 52);
		panel.add(lblSignUp);
		
		JLabel lblUsername = new JLabel("Firstname");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblUsername.setBounds(431, 138, 155, 28);
		panel.add(lblUsername);
		
		txt_username = new JTextField();
		txt_username.setFont(new Font("Calibri", Font.PLAIN, 16));
		txt_username.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txt_username.setBounds(431, 84, 317, 43);
		panel.add(txt_username);
		txt_username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Email Address");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblPassword.setBounds(431, 215, 155, 20);
		panel.add(lblPassword);
		
		JButton btn_next = new JButton("Next");
		btn_next.setBorder(null);
		btn_next.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btn_next.setIconTextGap(16);
		btn_next.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_next.setIcon(new ImageIcon(Register.class.getResource("/image/next.png")));
		btn_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txt_username.getText();
				String firstname = txt_firstname.getText();
				String lastname = txt_lastname.getText();
				String email = txt_email.getText();
				String password = txt_password.getText();
				String password2 = txt_password2.getText();
				if(validation()) {
					AuthService ls=new Auth();
					UserModel user = new UserModel();
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
					LocalDateTime date = LocalDateTime.now();  
					     
					user.setUsername(username);
					user.setFirstname(firstname);
					user.setLastname(lastname);
					user.setEmail(email);
					user.setPassword(Encryption.MD5(password));
					user.setPhone("");
					user.setTax_file_number("");
					
					user.setDate_of_birth(date.toString());					
					user.setAddress("");
					user.setUser_type("employee");
					if(ls.addUser(user)){
						
						new AdditionalInformation().setVisible(true);
						dispose();						
						
					}else{
						JOptionPane.showMessageDialog(null, "Login failed");
					}		
						
						
					
				}
				
			}
		});
		btn_next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_next.setForeground(Color.WHITE);
		btn_next.setFont(new Font("Calibri", Font.PLAIN, 16));
		btn_next.setBackground(new Color(0, 102, 204));
		btn_next.setBounds(609, 429, 139, 43);
		panel.add(btn_next);
		
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
		
		JLabel label = new JLabel("Password");
		label.setFont(new Font("Calibri", Font.PLAIN, 16));
		label.setBounds(431, 287, 155, 20);
		panel.add(label);
		
		txt_password = new JPasswordField();
		txt_password.setBounds(431, 304, 317, 43);
		panel.add(txt_password);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(431, 358, 155, 20);
		panel.add(lblConfirmPassword);
		
		txt_password2 = new JPasswordField();
		txt_password2.setBounds(431, 375, 317, 43);
		panel.add(txt_password2);
		
		txt_email = new JTextField();
		txt_email.setFont(new Font("Calibri", Font.PLAIN, 16));
		txt_email.setColumns(10);
		txt_email.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txt_email.setBounds(431, 233, 317, 43);
		panel.add(txt_email);
		
		JLabel lblCreateNewUser = new JLabel("Back to login");
		lblCreateNewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		lblCreateNewUser.setBounds(431, 436, 168, 28);
		panel.add(lblCreateNewUser);
		lblCreateNewUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCreateNewUser.setForeground(new Color(57,145,230));
		lblCreateNewUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblCreateNewUser.setFont(new Font("Calibri", Font.BOLD, 12));
		
		txt_firstname = new JTextField();
		txt_firstname.setFont(new Font("Calibri", Font.PLAIN, 16));
		txt_firstname.setColumns(10);
		txt_firstname.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txt_firstname.setBounds(431, 161, 155, 43);
		panel.add(txt_firstname);
		
		JLabel label_1 = new JLabel("Username");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		label_1.setBounds(431, 62, 155, 28);
		panel.add(label_1);
		
		txt_lastname = new JTextField();
		txt_lastname.setFont(new Font("Calibri", Font.PLAIN, 16));
		txt_lastname.setColumns(10);
		txt_lastname.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txt_lastname.setBounds(593, 161, 155, 43);
		panel.add(txt_lastname);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblLastname.setBounds(596, 138, 155, 28);
		panel.add(lblLastname);
		setUndecorated(true);
	}

	protected boolean validation() {
		
		if(txt_username.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Username");
			return false;
		}
		if(txt_firstname.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Firstname");
			return false;
		}
		if(txt_lastname.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Lastname");
			return false;
		}
		if(txt_email.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Email Address");
			return false;
		}	
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
}
