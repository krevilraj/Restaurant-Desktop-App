package com.test.client.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.border.EtchedBorder;

import com.test.client.jframes.CustomItemEarnTable;
import com.test.client.jframes.CustomItemSoldTable;
import com.test.client.jframes.TodayItemEarnTable;
import com.test.client.jframes.TodayItemSoldTable;
import com.test.client.jframes.YesterdayItemEarnTable;
import com.test.client.jframes.YesterdayItemSoldTable;
import com.test.serviceimpl.ReportServiceimpl;
import com.test.services.ReportService;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Reports extends JPanel {

	/**
	 * Create the panel.
	 */

	private JDateChooser dcStartFrom, dcEnd, dcStartFrom1, dcEnd1;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public Reports() {
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblHome = new JLabel("Reports Management");
		lblHome.setFont(new Font("Calibri", Font.BOLD, 32));
		lblHome.setBounds(21, 11, 403, 51);
		add(lblHome);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Custom",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(42, 414, 825, 134);
		add(panel_2);

		JLabel label_6 = new JLabel("Most  requested item");
		label_6.setBounds(28, 23, 156, 14);
		panel_2.add(label_6);

		JButton button_8 = new JButton("Table");
		button_8.setBorder(null);
		button_8.setIcon(new ImageIcon(Reports.class.getResource("/image/table.png")));
		button_8.setIconTextGap(16);
		button_8.setHorizontalTextPosition(SwingConstants.LEFT);
		button_8.setForeground(Color.WHITE);
		button_8.setFont(new Font("Calibri", Font.PLAIN, 14));
		button_8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_8.setBackground(new Color(19, 122, 222));
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (soldValidation()) {
					String startFrom = df.format(dcStartFrom.getDate());
					String endAt = df.format(dcEnd.getDate());
					new CustomItemSoldTable().displayData(startFrom, endAt);

				}
			}
		});
		button_8.setBounds(97, 85, 125, 39);
		panel_2.add(button_8);

		JButton button_9 = new JButton("Graph");
		button_9.setBorder(null);
		button_9.setIcon(new ImageIcon(Reports.class.getResource("/image/graph.png")));
		button_9.setIconTextGap(16);
		button_9.setHorizontalTextPosition(SwingConstants.LEFT);
		button_9.setForeground(Color.WHITE);
		button_9.setFont(new Font("Calibri", Font.PLAIN, 14));
		button_9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_9.setBackground(new Color(19, 122, 222));
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (soldValidation()) {
					showCustomSoldGraph();

				}
			}
		});
		button_9.setBounds(232, 85, 125, 39);
		panel_2.add(button_9);

		JLabel label_7 = new JLabel("View");
		label_7.setBounds(28, 97, 59, 14);
		panel_2.add(label_7);

		JLabel label_8 = new JLabel("Most  earn by item");
		label_8.setBounds(423, 23, 156, 14);
		panel_2.add(label_8);

		JLabel lblStartFrom = new JLabel("Start From");
		lblStartFrom.setBounds(26, 60, 70, 14);
		panel_2.add(lblStartFrom);

		dcStartFrom = new JDateChooser();
		dcStartFrom.setBounds(112, 57, 91, 20);
		dcStartFrom.setEnabled(false);
		dcStartFrom.getCalendarButton().setEnabled(true);
		panel_2.add(dcStartFrom);

		JLabel lblEndAt = new JLabel("End at");
		lblEndAt.setBounds(240, 60, 70, 14);
		panel_2.add(lblEndAt);

		dcEnd = new JDateChooser();
		dcEnd.setBounds(307, 57, 91, 20);
		dcEnd.setEnabled(false);
		dcEnd.getCalendarButton().setEnabled(true);
		panel_2.add(dcEnd);

		JLabel label_9 = new JLabel("Start From");
		label_9.setBounds(423, 60, 70, 14);
		panel_2.add(label_9);

		JLabel label_11 = new JLabel("View");
		label_11.setBounds(425, 97, 59, 14);
		panel_2.add(label_11);

		JButton button_10 = new JButton("Table");
		button_10.setBorder(null);
		button_10.setIcon(new ImageIcon(Reports.class.getResource("/image/table.png")));
		button_10.setIconTextGap(16);
		button_10.setHorizontalTextPosition(SwingConstants.LEFT);
		button_10.setForeground(Color.WHITE);
		button_10.setFont(new Font("Calibri", Font.PLAIN, 14));
		button_10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_10.setBackground(new Color(19, 122, 222));
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(earnValidation()) {
					String startFrom = df.format(dcStartFrom1.getDate());
					String endAt = df.format(dcEnd1.getDate());
					new CustomItemEarnTable().displayData(startFrom, endAt);
				}
			}
		});
		button_10.setBounds(511, 85, 125, 39);
		panel_2.add(button_10);

		dcStartFrom1 = new JDateChooser();
		dcStartFrom1.setBounds(509, 57, 91, 20);
		dcStartFrom1.setEnabled(false);
		dcStartFrom1.getCalendarButton().setEnabled(true);
		panel_2.add(dcStartFrom1);

		JLabel lblEnd = new JLabel("End at");
		lblEnd.setBounds(637, 60, 70, 14);
		panel_2.add(lblEnd);

		JButton button_11 = new JButton("Graph");
		button_11.setBorder(null);
		button_11.setIcon(new ImageIcon(Reports.class.getResource("/image/graph.png")));
		button_11.setIconTextGap(16);
		button_11.setHorizontalTextPosition(SwingConstants.LEFT);
		button_11.setForeground(Color.WHITE);
		button_11.setFont(new Font("Calibri", Font.PLAIN, 14));
		button_11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_11.setBackground(new Color(19, 122, 222));
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(earnValidation()) {
					showCustomEarnGraph();
				}
			}
		});
		button_11.setBounds(647, 85, 125, 39);
		panel_2.add(button_11);

		dcEnd1 = new JDateChooser();
		dcEnd1.setBounds(704, 57, 91, 20);
		dcEnd1.setEnabled(false);
		dcEnd1.getCalendarButton().setEnabled(true);
		panel_2.add(dcEnd1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255,200));
		panel_3.setBounds(21, 67, 858, 511);
		add(panel_3);
		panel_3.setLayout(null);
		
		

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(23, 205, 825, 134);
		panel_3.add(panel_1);
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Yesterday",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
				JLabel label = new JLabel("Most  requested item");
				label.setBounds(28, 37, 156, 14);
				panel_1.add(label);
				
						JButton button_4 = new JButton("Table");
						button_4.setBorder(null);
						button_4.setIcon(new ImageIcon(Reports.class.getResource("/image/table.png")));
						button_4.setIconTextGap(16);
						button_4.setHorizontalTextPosition(SwingConstants.LEFT);
						button_4.setForeground(Color.WHITE);
						button_4.setFont(new Font("Calibri", Font.PLAIN, 14));
						button_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						button_4.setBackground(new Color(19, 122, 222));
						button_4.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								new YesterdayItemSoldTable().setVisible(true);
							}
						});
						button_4.setBounds(97, 67, 125, 39);
						panel_1.add(button_4);
						
								JButton button_5 = new JButton("Graph");
								button_5.setBorder(null);
								button_5.setIcon(new ImageIcon(Reports.class.getResource("/image/graph.png")));
								button_5.setIconTextGap(16);
								button_5.setHorizontalTextPosition(SwingConstants.LEFT);
								button_5.setForeground(Color.WHITE);
								button_5.setFont(new Font("Calibri", Font.PLAIN, 14));
								button_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								button_5.setBackground(new Color(19, 122, 222));
								button_5.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										showYesterdayCountGraph();
									}
								});
								button_5.setBounds(232, 65, 125, 39);
								panel_1.add(button_5);
								
										JLabel label_2 = new JLabel("View");
										label_2.setBounds(28, 71, 59, 14);
										panel_1.add(label_2);
										
												JLabel label_4 = new JLabel("Most  earn by item");
												label_4.setBounds(426, 37, 156, 14);
												panel_1.add(label_4);
												
														JLabel label_5 = new JLabel("View");
														label_5.setBounds(426, 71, 59, 14);
														panel_1.add(label_5);
														
																JButton button_6 = new JButton("Table");
																button_6.setBorder(null);
																button_6.setIcon(new ImageIcon(Reports.class.getResource("/image/table.png")));
																button_6.setIconTextGap(16);
																button_6.setHorizontalTextPosition(SwingConstants.LEFT);
																button_6.setForeground(Color.WHITE);
																button_6.setFont(new Font("Calibri", Font.PLAIN, 14));
																button_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
																button_6.setBackground(new Color(19, 122, 222));
																button_6.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {
																		new YesterdayItemEarnTable().setVisible(true);
																	}
																});
																button_6.setBounds(512, 67, 125, 39);
																panel_1.add(button_6);
																
																		JButton button_7 = new JButton("Graph");
																		button_7.setBorder(null);
																		button_7.setIcon(new ImageIcon(Reports.class.getResource("/image/graph.png")));
																		button_7.setIconTextGap(16);
																		button_7.setHorizontalTextPosition(SwingConstants.LEFT);
																		button_7.setForeground(Color.WHITE);
																		button_7.setFont(new Font("Calibri", Font.PLAIN, 14));
																		button_7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
																		button_7.setBackground(new Color(19, 122, 222));
																		button_7.addActionListener(new ActionListener() {
																			public void actionPerformed(ActionEvent e) {
																				showYesterdayEarnGraph();
																			}
																		});
																		button_7.setBounds(647, 67, 125, 39);
																		panel_1.add(button_7);
																		
																				JPanel panel = new JPanel();
																				panel.setBounds(23, 60, 825, 134);
																				panel_3.add(panel);
																				panel.setOpaque(false);
																				panel.setBorder(new TitledBorder(null, "Today", TitledBorder.LEADING, TitledBorder.TOP, null, null));
																				panel.setLayout(null);
																				
																						JLabel lblMostRequestedItem = new JLabel("Most  requested item");
																						lblMostRequestedItem.setBounds(28, 37, 156, 14);
																						panel.add(lblMostRequestedItem);
																						
																								JButton button_2 = new JButton("Table");
																								button_2.setBorder(null);
																								button_2.setIcon(new ImageIcon(Reports.class.getResource("/image/table.png")));
																								button_2.setIconTextGap(16);
																								button_2.setHorizontalTextPosition(SwingConstants.LEFT);
																								button_2.setForeground(Color.WHITE);
																								button_2.setFont(new Font("Calibri", Font.PLAIN, 14));
																								button_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
																								button_2.setBackground(new Color(19, 122, 222));
																								
																								
																								button_2.addActionListener(new ActionListener() {
																									public void actionPerformed(ActionEvent e) {
																										new TodayItemSoldTable().setVisible(true);
																									}
																								});
																								button_2.setBounds(97, 67, 125, 39);
																								panel.add(button_2);
																								
																										JButton button_3 = new JButton("Graph");
																										button_3.setBorder(null);
																										button_3.setIcon(new ImageIcon(Reports.class.getResource("/image/graph.png")));
																										button_3.setIconTextGap(16);
																										button_3.setHorizontalTextPosition(SwingConstants.LEFT);
																										button_3.setForeground(Color.WHITE);
																										button_3.setFont(new Font("Calibri", Font.PLAIN, 14));
																										button_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
																										button_3.setBackground(new Color(19, 122, 222));
																										button_3.addActionListener(new ActionListener() {
																											public void actionPerformed(ActionEvent e) {
																												showTodayCountGraph();
																											}
																										});
																										button_3.setBounds(232, 67, 125, 39);
																										panel.add(button_3);
																										
																												JLabel label_3 = new JLabel("View");
																												label_3.setBounds(28, 71, 59, 14);
																												panel.add(label_3);
																												
																														JLabel lblMostRequestedItem_1 = new JLabel("Most  earn by item");
																														lblMostRequestedItem_1.setBounds(426, 37, 156, 14);
																														panel.add(lblMostRequestedItem_1);
																														
																																JLabel label_1 = new JLabel("View");
																																label_1.setBounds(426, 71, 59, 14);
																																panel.add(label_1);
																																
																																		JButton button = new JButton("Table");
																																		button.setBorder(null);
																																		button.setIcon(new ImageIcon(Reports.class.getResource("/image/table.png")));
																																		button.setIconTextGap(16);
																																		button.setHorizontalTextPosition(SwingConstants.LEFT);
																																		button.setForeground(Color.WHITE);
																																		button.setFont(new Font("Calibri", Font.PLAIN, 14));
																																		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
																																		button.setBackground(new Color(19, 122, 222));
																																		button.addActionListener(new ActionListener() {
																																			public void actionPerformed(ActionEvent e) {
																																				new TodayItemEarnTable().setVisible(true);
																																			}
																																		});
																																		button.setBounds(512, 67, 125, 39);
																																		panel.add(button);
																																		
																																				JButton button_1 = new JButton("Graph");
																																				button_1.setBorder(null);
																																				button_1.setIcon(new ImageIcon(Reports.class.getResource("/image/graph.png")));
																																				button_1.setIconTextGap(16);
																																				button_1.setHorizontalTextPosition(SwingConstants.LEFT);
																																				button_1.setForeground(Color.WHITE);
																																				button_1.setFont(new Font("Calibri", Font.PLAIN, 14));
																																				button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
																																				button_1.setBackground(new Color(19, 122, 222));
																																				button_1.addActionListener(new ActionListener() {
																																					public void actionPerformed(ActionEvent e) {
																																						showTodayEarnGraph();
																																					}
																																				});
																																				button_1.setBounds(647, 65, 125, 39);
																																				panel.add(button_1);
																																				
																																				JPanel panel_4 = new JPanel();
																																				panel_4.setBackground(new Color(68, 130, 236));
																																				panel_4.setBounds(25, 37, 149, 2);
																																				panel_3.add(panel_4);
																																				
																																				JLabel lblReports = new JLabel("Reports");
																																				lblReports.setForeground(new Color(67, 70, 86));
																																				lblReports.setFont(new Font("Calibri", Font.BOLD, 21));
																																				lblReports.setBounds(25, 2, 130, 47);
																																				panel_3.add(lblReports);
		JLabel label_33 = new JLabel("");
		label_33.setIcon(new ImageIcon(Drinks.class.getResource("/image/background2.jpg")));
		label_33.setBounds(0, 0, 1356, 704);
		add(label_33);

	}


	protected void showCustomEarnGraph() {
		String startFrom = df.format(dcStartFrom1.getDate());
		String endAt = df.format(dcEnd1.getDate());
		
		ReportService rs = new ReportServiceimpl();
		DefaultCategoryDataset dataset = rs.getCustomEarnGraph(startFrom, endAt);
		
		JFreeChart chart = ChartFactory.createBarChart("Item sold", "Item Name", "Money", dataset,
				PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot p = chart.getCategoryPlot();
		p.setRangeGridlinePaint(Color.black);
		ChartFrame frame = new ChartFrame("Today's Bar Chart for Item sold", chart);
		frame.setVisible(true);
		frame.setSize(902, 612);
		
		
	}

	protected void showCustomSoldGraph() {
		String startFrom = df.format(dcStartFrom.getDate());
		String endAt = df.format(dcEnd.getDate());
		
		ReportService rs = new ReportServiceimpl();
		DefaultCategoryDataset dataset = rs.getCustomSoldGraph(startFrom, endAt);

		JFreeChart chart = ChartFactory.createBarChart("Item sold times", "Item Name", "Count", dataset,
				PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis yAxis = p.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		p.setRangeGridlinePaint(Color.black);
		ChartFrame frame = new ChartFrame("Custom Bar Chart for Item sold", chart);
		frame.setVisible(true);
		frame.setSize(902, 612);
		
	}

	protected boolean soldValidation() {	

		
		if (this.dcStartFrom.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Please choose start from date");
			return false;
		}
		if (this.dcEnd.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Please choose end at date");
			return false;
		}
		String startFrom = df.format(dcStartFrom.getDate());
		String endAt = df.format(dcEnd.getDate());
		if (!com.test.helper.Date.compareDates(startFrom, endAt)) {
			JOptionPane.showMessageDialog(null, "Please start should greater than end date");
			return false;
		}

		return true;
	}

	protected boolean earnValidation() {
		if (this.dcStartFrom1.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Please choose start from date");
			return false;
		}
		if (this.dcEnd1.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Please choose end at date");
			return false;
		}
		String startFrom = df.format(dcStartFrom1.getDate());
		String endAt = df.format(dcEnd1.getDate());
		if (!com.test.helper.Date.compareDates(startFrom, endAt)) {
			JOptionPane.showMessageDialog(null, "Please start should greater than end date");
			return false;
		}
		return true;
	}

	protected void showTodayCountGraph() {
		ReportService rs = new ReportServiceimpl();
		DefaultCategoryDataset dataset = rs.getGraphItemSoldToday();

		JFreeChart chart = ChartFactory.createBarChart("Item sold times", "Item Name", "Count", dataset,
				PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis yAxis = p.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		p.setRangeGridlinePaint(Color.black);
		ChartFrame frame = new ChartFrame("Today's Bar Chart for Item sold", chart);
		frame.setVisible(true);
		frame.setSize(902, 612);

	}

	protected void showTodayEarnGraph() {
		ReportService rs = new ReportServiceimpl();
		DefaultCategoryDataset dataset = rs.getGraphItemEarnToday();

		JFreeChart chart = ChartFactory.createBarChart("Item sold", "Item Name", "Money", dataset,
				PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot p = chart.getCategoryPlot();
		p.setRangeGridlinePaint(Color.black);
		ChartFrame frame = new ChartFrame("Today's Bar Chart for Item sold", chart);
		frame.setVisible(true);
		frame.setSize(902, 612);
	}

	protected void showYesterdayCountGraph() {
		ReportService rs = new ReportServiceimpl();
		DefaultCategoryDataset dataset = rs.getGraphItemSoldYesterday();

		JFreeChart chart = ChartFactory.createBarChart("Item sold times", "Item Name", "Count", dataset,
				PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot p = chart.getCategoryPlot();
		ValueAxis yAxis = p.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		p.setRangeGridlinePaint(Color.black);
		ChartFrame frame = new ChartFrame("Yesterday's Bar Chart for Item sold", chart);
		frame.setVisible(true);
		frame.setSize(902, 612);

	}

	protected void showYesterdayEarnGraph() {
		ReportService rs = new ReportServiceimpl();
		DefaultCategoryDataset dataset = rs.getGraphItemEarnYesterday();

		JFreeChart chart = ChartFactory.createBarChart("Item sold", "Item Name", "Money", dataset,
				PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot p = chart.getCategoryPlot();
		p.setRangeGridlinePaint(Color.black);
		ChartFrame frame = new ChartFrame("Yesterday's Bar Chart for Item sold", chart);
		frame.setVisible(true);
		frame.setSize(902, 612);
	}
}
