package com.test.client.panels;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SalesPersonMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public SalesPersonMenu() {
		setLayout(null);
		
		JButton button = new JButton("Home                           ");
		button.setSelected(true);
		button.setIconTextGap(16);
		button.setHorizontalTextPosition(SwingConstants.RIGHT);
		button.setHorizontalAlignment(SwingConstants.RIGHT);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		button.setFocusPainted(false);
		button.setBorder(null);
		button.setBackground(new Color(7, 100, 187));
		button.setBounds(0, 0, 223, 40);
		add(button);
		
		JButton button_1 = new JButton("Take Order                   ");
		button_1.setSelected(true);
		button_1.setIconTextGap(16);
		button_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		button_1.setHorizontalAlignment(SwingConstants.RIGHT);
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		button_1.setFocusPainted(false);
		button_1.setBorder(null);
		button_1.setBackground(new Color(7, 100, 187));
		button_1.setBounds(0, 41, 223, 40);
		add(button_1);
		
		JButton button_2 = new JButton(" Process Order              ");
		button_2.setSelected(true);
		button_2.setIconTextGap(16);
		button_2.setHorizontalTextPosition(SwingConstants.RIGHT);
		button_2.setHorizontalAlignment(SwingConstants.RIGHT);
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		button_2.setFocusPainted(false);
		button_2.setBorder(null);
		button_2.setBackground(new Color(7, 100, 187));
		button_2.setBounds(0, 82, 223, 40);
		add(button_2);
		
		JButton button_3 = new JButton("Stock                           ");
		button_3.setSelected(true);
		button_3.setIconTextGap(16);
		button_3.setHorizontalTextPosition(SwingConstants.RIGHT);
		button_3.setHorizontalAlignment(SwingConstants.RIGHT);
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		button_3.setFocusPainted(false);
		button_3.setBorder(null);
		button_3.setBackground(new Color(7, 100, 187));
		button_3.setBounds(0, 123, 223, 43);
		add(button_3);
		setVisible(true);
	}

}
