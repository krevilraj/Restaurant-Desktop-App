package com.test.helper;

import javax.swing.JPanel;

import com.test.client.panels.CambiaPanel;

public class PannelChange {
	public static void change(JPanel main_panel,JPanel content) {
		new CambiaPanel(main_panel, content);
	}
}
