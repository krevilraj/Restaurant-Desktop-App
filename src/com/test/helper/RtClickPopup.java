package com.test.helper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

public class RtClickPopup extends JPopupMenu {

	public RtClickPopup(JTable table) {
		JMenuItem add = new JMenuItem("Add");
		JMenuItem edit = new JMenuItem("Edit");
		JMenuItem delete = new JMenuItem("Delete");

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(add, "Added");

			}
		});
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(edit, "Added");

			}
		});
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(delete, "Added");

			}
		});
	}
}
