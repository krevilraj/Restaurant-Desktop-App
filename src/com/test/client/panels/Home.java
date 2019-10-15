package com.test.client.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Home extends JPanel {

	/**
	 * Create the panel.
	 */
	public Home() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Home.class.getResource("/image/dashboard1.jpg")));
		label.setBounds(0, 0, 1130, 752);
		add(label);
		setVisible(true);

	}
}
