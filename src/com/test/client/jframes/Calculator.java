package com.test.client.jframes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calculator extends JFrame {

	private JPanel contentPane;
	private JTextField txt_calculate;
	private Double customer_price=0.0,total_price=0.0,temp=0.0;
	private JLabel txt_total_price,txt_return_price;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
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
	public Calculator() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 416, 564);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txt_calculate = new JTextField();
		txt_calculate.setBounds(21, 52, 371, 41);
		panel.add(txt_calculate);
		txt_calculate.setColumns(10);
		
		JLabel lblCalculator = new JLabel("Calculator");
		lblCalculator.setBounds(25, 11, 157, 32);
		panel.add(lblCalculator);
		lblCalculator.setFont(new Font("Calibri", Font.BOLD, 26));
		
		JButton btn_5 = new JButton("$5");
		btn_5.setBorder(null);
		btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(5.0);
			}
		});
		btn_5.setIconTextGap(16);
		btn_5.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_5.setForeground(Color.WHITE);
		btn_5.setFont(new Font("Calibri", Font.PLAIN, 16));
		btn_5.setBackground(new Color(0, 102, 204));
		btn_5.setAlignmentY(1.0f);
		btn_5.setBounds(21, 133, 77, 43);
		panel.add(btn_5);
		
		JButton btn_10 = new JButton("$10");
		btn_10.setBorder(null);
		btn_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(10.0);
			}
		});
		btn_10.setIconTextGap(16);
		btn_10.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_10.setForeground(Color.WHITE);
		btn_10.setFont(new Font("Calibri", Font.PLAIN, 16));
		btn_10.setBackground(new Color(0, 102, 204));
		btn_10.setAlignmentY(1.0f);
		btn_10.setBounds(119, 133, 77, 43);
		panel.add(btn_10);
		
		JButton btn_20 = new JButton("$20");
		btn_20.setBorder(null);
		btn_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(20.0);
			}
		});
		btn_20.setIconTextGap(16);
		btn_20.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_20.setForeground(Color.WHITE);
		btn_20.setFont(new Font("Calibri", Font.PLAIN, 16));
		btn_20.setBackground(new Color(0, 102, 204));
		btn_20.setAlignmentY(1.0f);
		btn_20.setBounds(217, 133, 77, 43);
		panel.add(btn_20);
		
		JButton btn_50 = new JButton("$50");
		btn_50.setBorder(null);
		btn_50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(50.0);
			}
		});
		btn_50.setIconTextGap(16);
		btn_50.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_50.setForeground(Color.WHITE);
		btn_50.setFont(new Font("Calibri", Font.PLAIN, 16));
		btn_50.setBackground(new Color(0, 102, 204));
		btn_50.setAlignmentY(1.0f);
		btn_50.setBounds(315, 133, 77, 43);
		panel.add(btn_50);
		
		JButton btn_100 = new JButton("$100");
		btn_100.setBorder(null);
		btn_100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(100.0);
			}
		});
		btn_100.setIconTextGap(16);
		btn_100.setHorizontalTextPosition(SwingConstants.LEFT);
		btn_100.setForeground(Color.WHITE);
		btn_100.setFont(new Font("Calibri", Font.PLAIN, 16));
		btn_100.setBackground(new Color(0, 102, 204));
		btn_100.setAlignmentY(1.0f);
		btn_100.setBounds(21, 187, 77, 43);
		panel.add(btn_100);
		
		JLabel lblCustom = new JLabel("Custom");
		lblCustom.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCustom.setBounds(21, 252, 113, 26);
		panel.add(lblCustom);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 182, 216));
		panel_1.setBounds(21, 38, 172, 4);
		panel.add(panel_1);
		
		JButton button_1 = new JButton("1");
		button_1.setBorder(null);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txt_calculate.setText(txt_calculate.getText()+""+1);				
				
			}
		});
		button_1.setIconTextGap(16);
		button_1.setHorizontalTextPosition(SwingConstants.LEFT);
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_1.setBackground(new Color(0, 102, 204));
		button_1.setAlignmentY(1.0f);
		button_1.setBounds(28, 292, 101, 43);
		panel.add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 182, 216));
		panel_2.setBounds(21, 275, 122, 4);
		panel.add(panel_2);
		
		JButton button_4 = new JButton("4");
		button_4.setBorder(null);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_calculate.setText(txt_calculate.getText()+""+4);
			}
		});
		button_4.setIconTextGap(16);
		button_4.setHorizontalTextPosition(SwingConstants.LEFT);
		button_4.setForeground(Color.WHITE);
		button_4.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_4.setBackground(new Color(0, 102, 204));
		button_4.setAlignmentY(1.0f);
		button_4.setBounds(28, 346, 101, 43);
		panel.add(button_4);
		
		JButton button_7 = new JButton("7");
		button_7.setBorder(null);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_calculate.setText(txt_calculate.getText()+""+7);
			}
		});
		button_7.setIconTextGap(16);
		button_7.setHorizontalTextPosition(SwingConstants.LEFT);
		button_7.setForeground(Color.WHITE);
		button_7.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_7.setBackground(new Color(0, 102, 204));
		button_7.setAlignmentY(1.0f);
		button_7.setBounds(28, 400, 101, 43);
		panel.add(button_7);
		
		JButton button_dot = new JButton(".");
		button_dot.setBorder(null);
		button_dot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_calculate.setText(txt_calculate.getText()+".");
			}
		});
		button_dot.setIconTextGap(16);
		button_dot.setHorizontalTextPosition(SwingConstants.LEFT);
		button_dot.setForeground(Color.WHITE);
		button_dot.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_dot.setBackground(new Color(0, 102, 204));
		button_dot.setAlignmentY(1.0f);
		button_dot.setBounds(28, 454, 101, 43);
		panel.add(button_dot);
		
		JButton button_2 = new JButton("2");
		button_2.setBorder(null);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_calculate.setText(txt_calculate.getText()+""+2);
			}
		});
		button_2.setIconTextGap(16);
		button_2.setHorizontalTextPosition(SwingConstants.LEFT);
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_2.setBackground(new Color(0, 102, 204));
		button_2.setAlignmentY(1.0f);
		button_2.setBounds(157, 292, 101, 43);
		panel.add(button_2);
		
		JButton button_5 = new JButton("5");
		button_5.setBorder(null);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_calculate.setText(txt_calculate.getText()+""+5);
			}
		});
		button_5.setIconTextGap(16);
		button_5.setHorizontalTextPosition(SwingConstants.LEFT);
		button_5.setForeground(Color.WHITE);
		button_5.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_5.setBackground(new Color(0, 102, 204));
		button_5.setAlignmentY(1.0f);
		button_5.setBounds(157, 346, 101, 43);
		panel.add(button_5);
		
		JButton button_8 = new JButton("8");
		button_8.setBorder(null);
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_calculate.setText(txt_calculate.getText()+""+8);
			}
		});
		button_8.setIconTextGap(16);
		button_8.setHorizontalTextPosition(SwingConstants.LEFT);
		button_8.setForeground(Color.WHITE);
		button_8.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_8.setBackground(new Color(0, 102, 204));
		button_8.setAlignmentY(1.0f);
		button_8.setBounds(157, 400, 101, 43);
		panel.add(button_8);
		
		JButton button_0 = new JButton("0");
		button_0.setBorder(null);
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_calculate.setText(txt_calculate.getText()+""+0);
			}
		});
		button_0.setIconTextGap(16);
		button_0.setHorizontalTextPosition(SwingConstants.LEFT);
		button_0.setForeground(Color.WHITE);
		button_0.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_0.setBackground(new Color(0, 102, 204));
		button_0.setAlignmentY(1.0f);
		button_0.setBounds(157, 454, 101, 43);
		panel.add(button_0);
		
		JButton button_3 = new JButton("3");
		button_3.setBorder(null);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_calculate.setText(txt_calculate.getText()+""+3);
			}
		});
		button_3.setIconTextGap(16);
		button_3.setHorizontalTextPosition(SwingConstants.LEFT);
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_3.setBackground(new Color(0, 102, 204));
		button_3.setAlignmentY(1.0f);
		button_3.setBounds(286, 292, 101, 43);
		panel.add(button_3);
		
		JButton button_6 = new JButton("6");
		button_6.setBorder(null);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_calculate.setText(txt_calculate.getText()+""+6);
			}
		});
		button_6.setIconTextGap(16);
		button_6.setHorizontalTextPosition(SwingConstants.LEFT);
		button_6.setForeground(Color.WHITE);
		button_6.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_6.setBackground(new Color(0, 102, 204));
		button_6.setAlignmentY(1.0f);
		button_6.setBounds(286, 346, 101, 43);
		panel.add(button_6);
		
		JButton button_9 = new JButton("9");
		button_9.setBorder(null);
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_calculate.setText(txt_calculate.getText()+""+9);
			}
		});
		button_9.setIconTextGap(16);
		button_9.setHorizontalTextPosition(SwingConstants.LEFT);
		button_9.setForeground(Color.WHITE);
		button_9.setFont(new Font("Calibri", Font.PLAIN, 16));
		button_9.setBackground(new Color(0, 102, 204));
		button_9.setAlignmentY(1.0f);
		button_9.setBounds(286, 400, 101, 43);
		panel.add(button_9);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBorder(null);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_calculate.setText("");
			}
		});
		btnClear.setIconTextGap(16);
		btnClear.setHorizontalTextPosition(SwingConstants.LEFT);
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnClear.setBackground(new Color(0, 102, 204));
		btnClear.setAlignmentY(1.0f);
		btnClear.setBounds(286, 454, 101, 43);
		panel.add(btnClear);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBorder(null);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Double inputfield = Double.parseDouble(txt_calculate.getText());
				Double toreturn = inputfield - total_price; 
				txt_return_price.setText(toreturn+"");
				txt_calculate.setText("");
				
			}
		});
		btnEnter.setIconTextGap(16);
		btnEnter.setHorizontalTextPosition(SwingConstants.LEFT);
		btnEnter.setForeground(Color.WHITE);
		btnEnter.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnEnter.setBackground(new Color(0, 182, 216));
		btnEnter.setAlignmentY(1.0f);
		btnEnter.setBounds(217, 510, 170, 43);
		panel.add(btnEnter);
		
		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setBounds(21, 104, 77, 14);
		panel.add(lblTotalPrice);
		
		txt_total_price = new JLabel("0.0");
		txt_total_price.setBounds(118, 104, 48, 14);
		panel.add(txt_total_price);
		
		JLabel lblToReturn = new JLabel("To Return:");
		lblToReturn.setBounds(217, 104, 77, 14);
		panel.add(lblToReturn);
		
		txt_return_price = new JLabel("0.0");
		txt_return_price.setBounds(317, 104, 75, 14);
		panel.add(txt_return_price);
		setVisible(true);
	}
	
	protected void add(double d) {
		this.temp += d;
		txt_calculate.setText(this.temp+"");
		
	}

	public void calculate(Double total_price) {
		this.total_price = total_price;
		txt_total_price.setText(total_price+"");
	}
}
