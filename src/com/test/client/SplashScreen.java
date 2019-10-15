package com.test.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import junit.awtui.ProgressBar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import java.awt.Font;
import java.io.IOException;

public class SplashScreen extends JFrame {

	private JPanel contentPane;
	private static JLabel lbl_number;
	private static JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		SplashScreen frame = new SplashScreen();

		try {
			Process process = Runtime.getRuntime()
					.exec("D:\\Important_project\\software\\xampp\\mysql\\bin\\mysqld.exe");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Xampp Server is not working");
		}
		try {
			for (int i = 0; i <= 100; i++) {

				Thread.sleep(50); // this will ensure the process sleep for 50 millisecond start progress value to
									// 0 to 100 %
				lbl_number.setText(Integer.toString(i) + "%"); // set the percent in number
				progressBar.setValue(i); // set move the progress bar by setting value.

				if (i == 100) { // after the loop is to 100 or i ==100 then dispose and open the Login jFrame
					new Login().setVisible(true); // open Login jFrame
					frame.dispose(); // dispose or close splashScreen jframe
				}

			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public SplashScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 303);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 122, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 204, 200));
		panel.setBounds(0, 11, 403, 303);
		contentPane.add(panel);
		panel.setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setBounds(0, 284, 403, 8);
		panel.add(progressBar);
		progressBar.setBackground(Color.WHITE);
		progressBar.setForeground(new Color(255, 124, 64));
		progressBar.setBorder(null);

		lbl_number = new JLabel("99%");
		lbl_number.setBounds(358, 255, 35, 22);
		panel.add(lbl_number);
		lbl_number.setForeground(Color.WHITE);
		lbl_number.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JLabel label = new JLabel("");
		label.setBounds(72, 11, 321, 214);
		panel.add(label);
		label.setIcon(new ImageIcon(SplashScreen.class.getResource("/image/logo_for_login.png")));

		JLabel lblStartingAfghanKebab = new JLabel("Starting Afghan kebab Software");
		lblStartingAfghanKebab.setForeground(Color.WHITE);
		lblStartingAfghanKebab.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblStartingAfghanKebab.setBounds(10, 255, 231, 22);
		panel.add(lblStartingAfghanKebab);
		
		
		setUndecorated(true);				 // this will remove maximize minimize and cross button from the window
		setVisible(true);					// jframe will be visible by this

	}
}
