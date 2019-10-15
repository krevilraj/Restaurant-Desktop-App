package com.test.client.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.util.List;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.test.client.Dashboard;
import com.test.client.ManagerDashboard;
import com.test.client.jframes.Calculator;
import com.test.client.jframes.Printer;
import com.test.helper.Date;
import com.test.models.OrderModel;
import com.test.models.ProductModel;
import com.test.models.ProductOrderModel;
import com.test.models.SettingModel;
import com.test.serviceimpl.OrderServiceimpl;
import com.test.serviceimpl.ProductOrderServiceimpl;
import com.test.serviceimpl.SettingServiceimpl;
import com.test.services.OrderService;
import com.test.services.ProductOrderService;
import com.test.services.SettingService;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class ManagerProcessOrders extends JPanel {
	private JTextField txt_item;
	private JTextField txt_price;
	private JTextField txt_quantity;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel model, model_1;
	private Integer product_id = -1, order_id = -1;
	private JRadioButton rdbtnCash, rdbtnCard, rdbtnMix, rdbtnNone;
	private JComboBox comboBoxStatus;
	private SettingModel setting;
	private JLabel txt_subtotal, txt_discount, txt_gst, txt_grandtotal;
	private JRadioButton rdbtnDinein, rdbtnTakeaway;
	private JTextField txt_table_no;

	/**
	 * Create the panel.
	 */
	public ManagerProcessOrders() {
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblHome = new JLabel("Order Process");
		lblHome.setFont(new Font("Calibri", Font.BOLD, 32));
		lblHome.setForeground(new Color(67, 70, 86));
		lblHome.setBounds(21, 11, 223, 51);
		add(lblHome);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		panel.setBounds(434, 59, 468, 553);
		add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 448, 258);
		panel.add(scrollPane);

		table_1 = new JTable();
		table_1.setSelectionForeground(Color.WHITE);
		table_1.setShowVerticalLines(false);
		table_1.setSelectionBackground(new Color(255, 104, 131));
		table_1.setRowMargin(0);
		table_1.setRowHeight(25);
		table_1.setIntercellSpacing(new Dimension(0, 0));
		table_1.setFocusable(false);
		table_1.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
		table_1.getTableHeader().setOpaque(false);
		table_1.getTableHeader().setBackground(new Color(0, 146, 230));
		table_1.getTableHeader().setForeground(new Color(255, 255, 255));
		model_1 = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Product Name", "Qty", "Price", "Type" });
		table_1.setModel(model_1);
		table_1.getColumnModel().getColumn(0).setMaxWidth(28);
		table_1.getColumnModel().getColumn(2).setMaxWidth(33);
		table_1.getColumnModel().getColumn(3).setMaxWidth(83);
		table_1.getColumnModel().getColumn(4).setMaxWidth(83);
		scrollPane.setViewportView(table_1);

		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					product_id = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
					populateOrderById(product_id);

				} catch (ArrayIndexOutOfBoundsException e1) {

				}

			}
		});

		JLabel lblOrderItems = new JLabel("Order Items");
		lblOrderItems.setForeground(new Color(67, 70, 86));
		lblOrderItems.setFont(new Font("Calibri", Font.BOLD, 21));
		lblOrderItems.setBounds(10, 0, 130, 47);
		panel.add(lblOrderItems);

		JLabel label_1 = new JLabel("Subtotal");
		label_1.setForeground(new Color(67, 70, 86));
		label_1.setFont(new Font("Calibri", Font.BOLD, 16));
		label_1.setBounds(10, 317, 77, 27);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Discount");
		label_2.setForeground(new Color(67, 70, 86));
		label_2.setFont(new Font("Calibri", Font.BOLD, 16));
		label_2.setBounds(10, 341, 77, 27);
		panel.add(label_2);

		JLabel label_3 = new JLabel("GST");
		label_3.setForeground(new Color(67, 70, 86));
		label_3.setFont(new Font("Calibri", Font.BOLD, 16));
		label_3.setBounds(10, 365, 77, 27);
		panel.add(label_3);

		JLabel label_4 = new JLabel("Total");
		label_4.setForeground(new Color(67, 70, 86));
		label_4.setFont(new Font("Calibri", Font.BOLD, 16));
		label_4.setBounds(10, 388, 77, 27);
		panel.add(label_4);

		txt_subtotal = new JLabel("0.0");
		txt_subtotal.setForeground(new Color(67, 70, 86));
		txt_subtotal.setFont(new Font("Calibri", Font.BOLD, 16));
		txt_subtotal.setBounds(107, 317, 77, 27);
		panel.add(txt_subtotal);

		txt_discount = new JLabel("0.0");
		txt_discount.setForeground(new Color(67, 70, 86));
		txt_discount.setFont(new Font("Calibri", Font.BOLD, 16));
		txt_discount.setBounds(107, 341, 77, 27);
		panel.add(txt_discount);

		txt_gst = new JLabel("0.0");
		txt_gst.setForeground(new Color(67, 70, 86));
		txt_gst.setFont(new Font("Calibri", Font.BOLD, 16));
		txt_gst.setBounds(107, 365, 77, 27);
		panel.add(txt_gst);

		txt_grandtotal = new JLabel("0.0");
		txt_grandtotal.setForeground(new Color(67, 70, 86));
		txt_grandtotal.setFont(new Font("Calibri", Font.BOLD, 16));
		txt_grandtotal.setBounds(107, 388, 77, 27);
		panel.add(txt_grandtotal);

		JLabel label_9 = new JLabel(":");
		label_9.setForeground(new Color(67, 70, 86));
		label_9.setFont(new Font("Calibri", Font.BOLD, 16));
		label_9.setBounds(97, 317, 12, 27);
		panel.add(label_9);

		JLabel label_10 = new JLabel(":");
		label_10.setForeground(new Color(67, 70, 86));
		label_10.setFont(new Font("Calibri", Font.BOLD, 16));
		label_10.setBounds(97, 341, 12, 27);
		panel.add(label_10);

		JLabel label_11 = new JLabel(":");
		label_11.setForeground(new Color(67, 70, 86));
		label_11.setFont(new Font("Calibri", Font.BOLD, 16));
		label_11.setBounds(97, 365, 12, 27);
		panel.add(label_11);

		JLabel label_12 = new JLabel(":");
		label_12.setForeground(new Color(67, 70, 86));
		label_12.setFont(new Font("Calibri", Font.BOLD, 16));
		label_12.setBounds(97, 388, 12, 27);
		panel.add(label_12);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(183, 348, 48, 14);
		panel.add(lblStatus);

		comboBoxStatus = new JComboBox();
		comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] { "Pending", "Approved", "Complete", "Cancel" }));
		comboBoxStatus.setBounds(268, 344, 190, 22);
		panel.add(comboBoxStatus);

		JButton button_6 = new JButton("Update Order");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (order_id != -1) {
					if (calculateGrandTotal()) {
						if (validationTable()) {
							OrderService os = new OrderServiceimpl();
							OrderModel order = new OrderModel();
							order.setId(order_id);
							order.setTotal_price(Double.parseDouble(txt_grandtotal.getText()));
							order.setStatus(comboBoxStatus.getSelectedItem().toString());
							order.setDate(Date.getDate());
							order.setTable_no(Integer.parseInt(txt_table_no.getText()));
							if (!os.updateOrder(order)) {
								JOptionPane.showMessageDialog(null, "Something went wrong");
							}
						}
						JOptionPane.showMessageDialog(null, "Order is updated Successfully!!!");
						ManagerDashboard.changeFrame(new ManagerProcessOrders());
					}
				} else {
					JOptionPane.showMessageDialog(null, "First choose order from the list.");
				}

			}
		});
		button_6.setForeground(Color.WHITE);
		button_6.setBackground(new Color(255, 124, 64));
		button_6.setBorder(null);
		button_6.setBounds(343, 500, 115, 36);
		panel.add(button_6);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new TitledBorder(null, "Payment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(183, 376, 275, 110);
		panel.add(panel_2);
		panel_2.setLayout(null);

		rdbtnCash = new JRadioButton("Cash");
		rdbtnCash.setBackground(Color.WHITE);
		rdbtnCash.setBounds(54, 26, 56, 23);
		panel_2.add(rdbtnCash);

		rdbtnCard = new JRadioButton("Card");
		rdbtnCard.setBackground(Color.WHITE);
		rdbtnCard.setBounds(164, 26, 56, 23);
		panel_2.add(rdbtnCard);

		rdbtnMix = new JRadioButton("Mix");
		rdbtnMix.setBackground(Color.WHITE);
		rdbtnMix.setBounds(54, 68, 56, 23);
		panel_2.add(rdbtnMix);

		rdbtnNone = new JRadioButton("None");
		rdbtnNone.setBackground(Color.WHITE);
		rdbtnNone.setBounds(164, 68, 56, 23);
		panel_2.add(rdbtnNone);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnCard);
		bg.add(rdbtnCash);
		bg.add(rdbtnMix);
		bg.add(rdbtnNone);

		JLabel lblTableNo = new JLabel("Table no.");
		lblTableNo.setBounds(183, 320, 75, 14);
		panel.add(lblTableNo);

		txt_table_no = new JTextField();
		txt_table_no.setBounds(268, 317, 96, 20);
		panel.add(txt_table_no);
		txt_table_no.setColumns(10);

		JLabel label_5 = new JLabel("");
		label_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String grandT = txt_grandtotal.getText();
				if (grandT.equals("0.0") || grandT.equals("")) {
					JOptionPane.showMessageDialog(null, "Please select order first");	
				} else {
					new Calculator().calculate(Double.parseDouble(txt_grandtotal.getText()));
				}
			}
		});
		label_5.setIcon(new ImageIcon(ManagerProcessOrders.class.getResource("/image/calculator.png")));
		label_5.setBounds(428, 0, 30, 47);
		panel.add(label_5);
		
		JButton button = new JButton("Generate Reciept");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (order_id != -1) {
					if (calculateGrandTotal()) {
						new Printer().displaybyId(order_id,txt_subtotal.getText(),txt_discount.getText(),txt_gst.getText(),txt_grandtotal.getText());
					}
				} else {
					JOptionPane.showMessageDialog(null, "First choose order from the list.");
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setBorder(null);
		button.setBackground(new Color(0, 102, 204));
		button.setBounds(183, 500, 115, 36);
		panel.add(button);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(68, 130, 236));
		panel_4.setBounds(10, 35, 149, 2);
		panel.add(panel_4);
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Order Id", "Status", "Table No.", "Date" });

		JPanel panel_1 = new JPanel();
//		panel_1.setOpaque(false);
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setLayout(null);
		panel_1.setBorder(null);
		panel_1.setBounds(21, 59, 389, 553);
		add(panel_1);

		JLabel label_13 = new JLabel("Product Name");
		label_13.setBounds(10, 347, 92, 14);
		panel_1.add(label_13);

		txt_item = new JTextField();
		txt_item.setColumns(10);
		txt_item.setBounds(101, 345, 133, 20);
		panel_1.add(txt_item);

		JLabel label_14 = new JLabel("Price");
		label_14.setBounds(10, 467, 48, 14);
		panel_1.add(label_14);

		txt_price = new JTextField();
		txt_price.setEnabled(false);
		txt_price.setColumns(10);
		txt_price.setBounds(101, 464, 133, 20);
		panel_1.add(txt_price);

		JLabel label_15 = new JLabel("Quantity");
		label_15.setBounds(10, 387, 48, 14);
		panel_1.add(label_15);

		txt_quantity = new JTextField();
		txt_quantity.setColumns(10);
		txt_quantity.setBounds(101, 389, 133, 20);
		panel_1.add(txt_quantity);

		JLabel label_16 = new JLabel("Deliver Type");
		label_16.setBounds(10, 427, 96, 14);
		panel_1.add(label_16);

		rdbtnDinein = new JRadioButton("Dine in");
		rdbtnDinein.setBackground(Color.WHITE);
		rdbtnDinein.setSelected(true);
		rdbtnDinein.setBounds(101, 423, 92, 23);
		panel_1.add(rdbtnDinein);

		rdbtnTakeaway = new JRadioButton("Take away");
		rdbtnTakeaway.setBackground(Color.WHITE);
		rdbtnTakeaway.setBounds(173, 423, 96, 23);
		panel_1.add(rdbtnTakeaway);

		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rdbtnDinein);
		bg1.add(rdbtnTakeaway);

		JButton btnUpdateProduct = new JButton("Update Item");
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (product_id != -1 && order_id != -1) {
					if (orderValidation()) {

						ProductOrderModel s = new ProductOrderModel();
						s.setId(product_id);
						s.setOrder_id(order_id);
						s.setPrice(Double.parseDouble(txt_price.getText()) * Integer.parseInt(txt_quantity.getText()));
						s.setProduct_name(txt_item.getText());
						s.setQuantity(Integer.parseInt(txt_quantity.getText()));
						if (rdbtnDinein.isSelected()) {
							s.setDeliver_type("Dine in");
						} else {
							s.setDeliver_type("Take away");
						}

						s.setUpdated_date(Date.getDate());

						ProductOrderService po = new ProductOrderServiceimpl();
						if (po.updateProductOrder(s)) {
							populateProductById(order_id);
							calculateGrandTotal();
							clearField();
							JOptionPane.showMessageDialog(null, "Product Item updated Successfully");
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "Cannot be update");
				}

			}
		});
		btnUpdateProduct.setForeground(Color.WHITE);
		btnUpdateProduct.setBackground(new Color(255, 124, 64));
		btnUpdateProduct.setBounds(240, 506, 115, 36);
		btnUpdateProduct.setBorder(null);
		panel_1.add(btnUpdateProduct);
		
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(10, 50, 370, 254);
				panel_1.add(scrollPane_1);
				
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
						table.setModel(model);
						table.getColumnModel().getColumn(0).setMaxWidth(68);
						scrollPane_1.setViewportView(table);
						
						JLabel lblOrders = new JLabel("Orders");
						lblOrders.setBounds(10, 0, 130, 47);
						panel_1.add(lblOrders);
						lblOrders.setForeground(new Color(67, 70, 86));
						lblOrders.setFont(new Font("Calibri", Font.BOLD, 21));
						
						JPanel panel_3 = new JPanel();
						panel_3.setBounds(10, 35, 149, 2);
						panel_1.add(panel_3);
						panel_3.setBackground(new Color(68, 130, 236));
						table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

							@Override
							public void valueChanged(ListSelectionEvent e) {

								try {
									order_id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
									populateProductById(order_id);

								} catch (ArrayIndexOutOfBoundsException e1) {

								}

							}
						});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ManagerProcessOrders.class.getResource("/image/processorder.jpg")));
//		lblNewLabel.setIcon(new ImageIcon(new ImageIcon("/image/bg.jpg").getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT)));
//		lblNewLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/image/background2.jpg")).getImage().getScaledInstance(1345, 768, Image.SCALE_SMOOTH)));
//		lblNewLabel.setIcon(new ImageIcon(ProcessOrders.class.getResource("/image/bg.jpg")));
		lblNewLabel.setBounds(0, 0, 1356, 704);
		add(lblNewLabel);
		populateOrder();
		initializeSetting();
	}

	protected void clearOrderField() {
		txt_table_no.setText(0 + "");
		comboBoxStatus.setSelectedIndex(0);
		order_id = 0;

	}

	protected boolean orderValidation() {
		if (txt_item.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please select Product from table");
			return false;
		}
		if (txt_price.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Price is not available");
			return false;
		}
		try {
			float product_price = Float.parseFloat(txt_price.getText());

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Price is not in correct form");
			return false;
		}
		if (txt_quantity.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write a quantity");
			return false;
		}
		try {
			float product_price = Float.parseFloat(txt_price.getText());

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Quantity is not a number");
			return false;
		}
		return true;
	}

	protected void clearField() {
		txt_item.setText("");
		txt_price.setText("");
		txt_quantity.setText("");
		rdbtnDinein.setSelected(true);

	}

	protected void populateOrderById(Integer product_id2) {
		product_id = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
		txt_item.setText(table_1.getValueAt(table_1.getSelectedRow(), 1).toString());
		Double one_price = Double.parseDouble(table_1.getValueAt(table_1.getSelectedRow(), 3).toString());
		Integer qty = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 2).toString());
		one_price = one_price / qty;
		txt_price.setText(one_price.toString());
		txt_quantity.setText(table_1.getValueAt(table_1.getSelectedRow(), 2).toString());
		String deliver_type = table_1.getValueAt(table_1.getSelectedRow(), 4).toString();
		if (deliver_type.equals("Dine in")) {
			rdbtnDinein.setSelected(true);
		} else {
			rdbtnTakeaway.setSelected(true);
		}

	}

	protected void populateProductById(Integer order_id2) {
		ProductOrderService ls = new ProductOrderServiceimpl();
		List<ProductOrderModel> sList = ls.getProductOrder(order_id2);
		model_1.setRowCount(0);
		for (ProductOrderModel s : sList) {
			model_1.addRow(new Object[] { s.getId(), s.getProduct_name(), s.getQuantity(), s.getPrice(),
					s.getDeliver_type() });
		}

		OrderService os = new OrderServiceimpl();
		OrderModel s = os.getById(order_id2);
		txt_table_no.setText(s.getTable_no() + "");
		comboBoxStatus.setSelectedItem(s.getStatus());
		if (s.getPaymentType() == null) {
			rdbtnNone.setSelected(true);
		} else if (s.getPaymentType().equals("Card")) {
			rdbtnCard.setSelected(true);
		} else if (s.getPaymentType().equals("Cash")) {
			rdbtnCash.setSelected(true);
		} else if (s.getPaymentType().equals("Mix")) {
			rdbtnMix.setSelected(true);
		}
		calculateGrandTotal();

	}

	private void populateOrder() {
		OrderService pos = new OrderServiceimpl();

		List<OrderModel> sList = pos.getOrder();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		for (OrderModel s : sList) {
			model.addRow(new Object[] { s.getId(), s.getStatus(), s.getTable_no(), s.getDate() });
		}

	}

	private void initializeSetting() {
		SettingService settingService = new SettingServiceimpl();
		setting = settingService.getSetting();

	}

	private boolean calculateGrandTotal() {
		Double subtotal = 0.0;
		Double discount = 0.0;
		Double itemGst = 0.0;
		Double grandTotal = 0.0;
		Double itemPrice = 0.0;
		Double afterGst = 0.0;
		Double taxable_amount = 0.0;

		for (int count = 0; count < model_1.getRowCount(); count++) {
			itemPrice = Double.parseDouble(model_1.getValueAt(count, 3).toString());
			subtotal += itemPrice;

		}
		discount = (subtotal * setting.getDiscount()) / 100;
		taxable_amount = subtotal - discount;
		itemGst += ((taxable_amount * setting.getGst()) / 100);

		grandTotal = itemGst + taxable_amount;

		txt_subtotal.setText(subtotal + "");
		txt_discount.setText(discount + "");
		txt_gst.setText(itemGst + "");
		txt_grandtotal.setText(grandTotal + "");

		return true;
	}

	protected boolean validationTable() {
		if (txt_table_no.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write table number");
			return false;
		}
		try {
			int product_price = Integer.parseInt(txt_table_no.getText());

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Table number is not a number");
			return false;
		}
		return true;
	}
}
