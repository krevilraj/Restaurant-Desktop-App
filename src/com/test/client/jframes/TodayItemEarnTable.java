package com.test.client.jframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.test.models.ItemCountModel;
import com.test.models.ItemEarnModel;
import com.test.models.ProductModel;
import com.test.serviceimpl.ProductServiceimpl;
import com.test.serviceimpl.ReportServiceimpl;
import com.test.services.ProductService;
import com.test.services.ReportService;

public class TodayItemEarnTable extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TodayItemEarnTable frame = new TodayItemEarnTable();
					frame.setVisible(true);
					frame.setTitle("Today's Earn by Item ");
					
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		});
	}

	protected void populateData() {
		ReportService ss = new ReportServiceimpl();
		List<ItemEarnModel> sList = ss.getTableItemEarnToday();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		int i = 1;
		for (ItemEarnModel s : sList) {
			model.addRow(new Object[] { i, s.getProduct_name(), s.getPrice() });
			i++;
		}
		
		
		
	}

	/**
	 * Create the frame.
	 */
	public TodayItemEarnTable() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 412, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 376, 540);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(255, 104, 131));
		table.setRowMargin(0);
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);
		table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(0, 146, 230));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SN.", "Item Name", "Earn"
			}
		));
		scrollPane.setViewportView(table);
		populateData();
	}
}
