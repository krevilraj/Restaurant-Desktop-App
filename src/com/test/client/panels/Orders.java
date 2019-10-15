package com.test.client.panels;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.test.client.Dashboard;
import com.test.helper.Date;
import com.test.helper.TableMouseListener;
import com.test.models.CategoryModel;
import com.test.models.OrderModel;
import com.test.models.ProductModel;
import com.test.models.ProductOrderModel;
import com.test.models.SettingModel;
import com.test.serviceimpl.CategoryServiceimpl;
import com.test.serviceimpl.DrinkServiceimpl;
import com.test.serviceimpl.OrderServiceimpl;
import com.test.serviceimpl.ProductOrderServiceimpl;
import com.test.serviceimpl.ProductServiceimpl;
import com.test.serviceimpl.SettingServiceimpl;
import com.test.services.CategoryService;
import com.test.services.DrinkService;
import com.test.services.OrderService;
import com.test.services.ProductOrderService;
import com.test.services.ProductService;
import com.test.services.SettingService;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Orders extends JPanel implements ActionListener {
	private JTable table;
	private JTextField txt_item;
	private JTextField txt_price;
	private JTextField txt_quantity;
	private JTable table_1;
	private JComboBox categoryComboBox;
	private JMenuItem menuItemRemove;
	private int order_id = -1, product_id = -1;
	private float total_price = 0;
	private boolean first_time = true;
	private JPopupMenu popupMenu;
	private DefaultTableModel model, model_1;
	private JRadioButton rdbtnDineIn, rdbtnTakeAway;
	private JLabel txt_subtotal, txt_discount, txt_gst, txt_grandtotal;
	private SettingModel setting;
	private JTextField txt_table_no;

	/**
	 * Create the panel.
	 */
	public Orders() {
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblHome = new JLabel("Order Management");
		lblHome.setFont(new Font("Calibri", Font.BOLD, 32));
		lblHome.setBounds(21, 11, 403, 51);
		add(lblHome);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 124, 434, 346);
		add(scrollPane);

		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(255, 104, 131));
		table.setRowMargin(0);
		table.setRowHeight(25);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFocusable(false);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(0, 146, 230));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Item", "Price", "Status" });
		table.setModel(model);
		table.getColumnModel().getColumn(0).setMaxWidth(32);
		table.getColumnModel().getColumn(1).setMaxWidth(300);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					String status = table.getValueAt(table.getSelectedRow(), 3).toString();
					if (status.equals("Unavailable")) {
						int input = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to add? It is not available in Stock");
						// 0=yes, 1=no, 2=cancel
						if (input == 0) {
							txt_item.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
							txt_price.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
							txt_quantity.setText(1 + "");
						}
					} else {
						txt_item.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
						txt_price.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
						txt_quantity.setText(1 + "");
					}
				} catch (ArrayIndexOutOfBoundsException e1) {

				}

			}
		});

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(21, 546, 451, 140);
		add(panel);

		JLabel label = new JLabel("Product Name");
		label.setBounds(21, 14, 92, 14);
		panel.add(label);

		txt_item = new JTextField();
		txt_item.setColumns(10);
		txt_item.setBounds(112, 11, 133, 20);
		panel.add(txt_item);

		JLabel label_2 = new JLabel("Price");
		label_2.setBounds(277, 14, 48, 14);
		panel.add(label_2);

		txt_price = new JTextField();
		txt_price.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "You cannot change Price");
			}
		});
		txt_price.setColumns(10);
		txt_price.setEnabled(false);
		txt_price.setBounds(345, 11, 96, 20);
		panel.add(txt_price);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(21, 42, 48, 14);
		panel.add(lblQuantity);

		txt_quantity = new JTextField();
		txt_quantity.setColumns(10);
		txt_quantity.setBounds(112, 39, 133, 20);
		panel.add(txt_quantity);

		JLabel lblDeliverType = new JLabel("Deliver Type");
		lblDeliverType.setBounds(21, 76, 96, 14);
		panel.add(lblDeliverType);

		rdbtnDineIn = new JRadioButton("Dine in");
		rdbtnDineIn.setBackground(Color.WHITE);
		rdbtnDineIn.setSelected(true);
		rdbtnDineIn.setBounds(112, 72, 92, 23);
		panel.add(rdbtnDineIn);

		rdbtnTakeAway = new JRadioButton("Take away");
		rdbtnTakeAway.setBackground(Color.WHITE);
		rdbtnTakeAway.setBounds(112, 97, 96, 23);
		panel.add(rdbtnTakeAway);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnDineIn);
		bg.add(rdbtnTakeAway);

		JButton btnAddToOrder = new JButton("Add to order");
		btnAddToOrder.setBorder(null);
		btnAddToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (orderValidation()) {					// validation if true then
					OrderService ls = new OrderServiceimpl();
					OrderModel s = new OrderModel();		// make Model by setting the value
					s.setTotal_price(Double.parseDouble(txt_price.getText()));		// setting price to model	
					s.setStatus("Pending");								// setting the status to model
					s.setDate(Date.getDate());							// setting the date to model
					total_price += Double.parseDouble(txt_price.getText());

					if (first_time) {
						order_id = ls.addOrder(s);
						if (order_id != -1) {
							ProductOrderModel pom = new ProductOrderModel();
							pom.setOrder_id(order_id);
							Double product_price = Double.parseDouble(txt_price.getText())
									* Integer.parseInt(txt_quantity.getText());
							pom.setPrice(product_price);
							pom.setProduct_name(txt_item.getText());
							pom.setQuantity(Integer.parseInt(txt_quantity.getText()));
							if (rdbtnDineIn.isSelected()) {
								pom.setDeliver_type("Dine in");
							} else {
								pom.setDeliver_type("Take away");
							}
							pom.setUpdated_date(Date.getDate());

							ProductOrderService po = new ProductOrderServiceimpl();
							if (po.addProductOrder(pom)) {
								populateProductOrder(order_id);
								calculateGrandTotal();
							}
							first_time = false;
						}
					} else {
						if (order_id != -1) {
							ProductOrderModel pom = new ProductOrderModel();
							pom.setOrder_id(order_id);
							pom.setPrice(
									Double.parseDouble(txt_price.getText()) * Integer.parseInt(txt_quantity.getText()));
							pom.setProduct_name(txt_item.getText());
							pom.setQuantity(Integer.parseInt(txt_quantity.getText()));
							if (rdbtnDineIn.isSelected()) {
								pom.setDeliver_type("Dine in");
							} else {
								pom.setDeliver_type("Take away");
							}
							pom.setUpdated_date(Date.getDate());

							ProductOrderService po = new ProductOrderServiceimpl();
							if (po.addProductOrder(pom)) {
								populateProductOrder(order_id);
								calculateGrandTotal();
							}
						}
					}
				}

			}
		});
		btnAddToOrder.setForeground(Color.WHITE);
		btnAddToOrder.setBackground(new Color(0, 102, 204));
		btnAddToOrder.setBounds(335, 81, 106, 36);
		panel.add(btnAddToOrder);

		JButton btnUpdateOrder = new JButton("Update Order");
		btnUpdateOrder.setBorder(null);
		btnUpdateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (product_id != -1 && order_id != -1) {
					if (orderValidation()) {

						ProductOrderModel s = new ProductOrderModel();
						s.setId(product_id);
						s.setOrder_id(order_id);
						s.setPrice(Double.parseDouble(txt_price.getText()) * Integer.parseInt(txt_quantity.getText()));
						s.setProduct_name(txt_item.getText());
						s.setQuantity(Integer.parseInt(txt_quantity.getText()));
						if (rdbtnDineIn.isSelected()) {
							s.setDeliver_type("Dine in");
						} else {
							s.setDeliver_type("Take away");
						}
						s.setUpdated_date(Date.getDate());
						ProductOrderService po = new ProductOrderServiceimpl();
						if (po.updateProductOrder(s)) {
							populateProductOrder(order_id);
							calculateGrandTotal();
							clearField();
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "Cannot be update");
				}
			}
		});
		btnUpdateOrder.setForeground(Color.WHITE);
		btnUpdateOrder.setBackground(new Color(255, 124, 64));
		btnUpdateOrder.setBounds(210, 81, 115, 36);
		panel.add(btnUpdateOrder);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255, 200));
		panel_1.setBounds(482, 72, 410, 614);
		add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 49, 390, 354);
		panel_1.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setSelectionForeground(Color.WHITE);
		table_1.setShowVerticalLines(false);
		table_1.setSelectionBackground(new Color(255, 104, 131));
		table_1.setRowMargin(0);
		table_1.setRowHeight(25);
		table_1.setIntercellSpacing(new Dimension(0, 0));
		table_1.setFocusable(false);
		table_1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
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
		scrollPane_1.setViewportView(table_1);

		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					int id = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
					product_id = id;
					txt_item.setText(table_1.getValueAt(table_1.getSelectedRow(), 1).toString());
					Double one_price = Double.parseDouble(table_1.getValueAt(table_1.getSelectedRow(), 3).toString());
					Integer qty = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 2).toString());
					one_price = one_price / qty;
					txt_price.setText(one_price.toString());
					txt_quantity.setText(table_1.getValueAt(table_1.getSelectedRow(), 2).toString());
					String deliver_type = table_1.getValueAt(table_1.getSelectedRow(), 4).toString();
					if (deliver_type.equals("Dine in")) {
						rdbtnDineIn.setSelected(true);
					} else {
						rdbtnTakeAway.setSelected(true);
					}
				} catch (ArrayIndexOutOfBoundsException e2) {

				}

			}
		});

		JButton btnProceedToKitchen = new JButton("Proceed to Kitchen");
		btnProceedToKitchen.setBorder(null);
		btnProceedToKitchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validationTable()) {
					OrderService os = new OrderServiceimpl();
					OrderModel order = new OrderModel();
					order.setId(order_id);
					order.setTotal_price(Double.parseDouble(txt_grandtotal.getText()));
					order.setStatus("Pending");
					order.setDate(Date.getDate());
					order.setTable_no(Integer.parseInt(txt_table_no.getText()));
					if (!os.updateOrder(order)) {
						JOptionPane.showMessageDialog(null, "Something went wrong");
					}
					Dashboard.changeFrame(new ProcessOrders());
				}

			}
		});
		btnProceedToKitchen.setForeground(Color.WHITE);
		btnProceedToKitchen.setBackground(new Color(0, 102, 204));
		btnProceedToKitchen.setBounds(248, 447, 152, 36);
		panel_1.add(btnProceedToKitchen);

		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setFont(new Font("Calibri", Font.BOLD, 16));
		lblSubtotal.setBounds(10, 414, 77, 27);
		panel_1.add(lblSubtotal);

		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setFont(new Font("Calibri", Font.BOLD, 16));
		lblDiscount.setBounds(10, 438, 77, 27);
		panel_1.add(lblDiscount);

		JLabel lblGst = new JLabel("GST");
		lblGst.setFont(new Font("Calibri", Font.BOLD, 16));
		lblGst.setBounds(10, 462, 77, 27);
		panel_1.add(lblGst);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Calibri", Font.BOLD, 16));
		lblTotal.setBounds(10, 485, 77, 27);
		panel_1.add(lblTotal);

		txt_subtotal = new JLabel("0.0");
		txt_subtotal.setFont(new Font("Calibri", Font.BOLD, 16));
		txt_subtotal.setBounds(146, 414, 77, 27);
		panel_1.add(txt_subtotal);

		txt_discount = new JLabel("0.0");
		txt_discount.setFont(new Font("Calibri", Font.BOLD, 16));
		txt_discount.setBounds(146, 438, 77, 27);
		panel_1.add(txt_discount);

		txt_gst = new JLabel("0.0");
		txt_gst.setFont(new Font("Calibri", Font.BOLD, 16));
		txt_gst.setBounds(146, 462, 77, 27);
		panel_1.add(txt_gst);

		txt_grandtotal = new JLabel("0.0");
		txt_grandtotal.setFont(new Font("Calibri", Font.BOLD, 16));
		txt_grandtotal.setBounds(146, 485, 77, 27);
		panel_1.add(txt_grandtotal);

		JLabel label_1 = new JLabel(":");
		label_1.setFont(new Font("Calibri", Font.BOLD, 16));
		label_1.setBounds(97, 414, 77, 27);
		panel_1.add(label_1);

		JLabel label_3 = new JLabel(":");
		label_3.setFont(new Font("Calibri", Font.BOLD, 16));
		label_3.setBounds(97, 438, 77, 27);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel(":");
		label_4.setFont(new Font("Calibri", Font.BOLD, 16));
		label_4.setBounds(97, 462, 77, 27);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel(":");
		label_5.setFont(new Font("Calibri", Font.BOLD, 16));
		label_5.setBounds(97, 485, 77, 27);
		panel_1.add(label_5);

		JButton btnCancelOrder = new JButton("Cancel Order");
		btnCancelOrder.setBorder(null);
		btnCancelOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderService orderservice = new OrderServiceimpl();
				if (orderservice.deleteOrder(order_id)) {
					JOptionPane.showMessageDialog(null, "Order has been Canceled");
					new Dashboard().changeFrame(new Orders());
				}
			}
		});
		btnCancelOrder.setForeground(Color.WHITE);
		btnCancelOrder.setBackground(new Color(255, 124, 64));
		btnCancelOrder.setBounds(248, 494, 152, 36);
		panel_1.add(btnCancelOrder);

		txt_table_no = new JTextField();
		txt_table_no.setText("0");
		txt_table_no.setBounds(357, 414, 43, 20);
		panel_1.add(txt_table_no);
		txt_table_no.setColumns(10);

		JLabel lblTableNo = new JLabel("Table no.");
		lblTableNo.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTableNo.setBounds(248, 414, 77, 22);
		panel_1.add(lblTableNo);

		JLabel lblOrders = new JLabel("Orders");
		lblOrders.setForeground(new Color(67, 70, 86));
		lblOrders.setFont(new Font("Calibri", Font.BOLD, 21));
		lblOrders.setBounds(10, 0, 130, 47);
		panel_1.add(lblOrders);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(68, 130, 236));
		panel_4.setBounds(10, 35, 149, 2);
		panel_1.add(panel_4);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255, 200));
		panel_2.setBounds(21, 73, 451, 614);
		add(panel_2);
		panel_2.setLayout(null);

		JLabel lblFilters = new JLabel("Filters");
		lblFilters.setBounds(15, 426, 56, 20);
		panel_2.add(lblFilters);
		lblFilters.setFont(new Font("Calibri", Font.BOLD, 16));

		categoryComboBox = new JComboBox();
		categoryComboBox.setBounds(86, 425, 156, 22);
		panel_2.add(categoryComboBox);
		categoryComboBox.setModel(new DefaultComboBoxModel(new String[] { "Category" }));

		JButton btnMain = new JButton("");
		btnMain.setBorder(null);
		btnMain.setBounds(257, 418, 48, 36);
		panel_2.add(btnMain);
		btnMain.setToolTipText("Main Offer Dish");
		btnMain.setIcon(new ImageIcon(Orders.class.getResource("/image/btn_main.png")));
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateAllMain();
			}
		});
		btnMain.setForeground(Color.WHITE);
		btnMain.setBackground(new Color(0, 102, 204));

		JButton btnDrinks = new JButton("");
		btnDrinks.setBorder(null);
		btnDrinks.setBounds(320, 418, 48, 36);
		panel_2.add(btnDrinks);
		btnDrinks.setToolTipText("Drinks");
		btnDrinks.setIcon(new ImageIcon(Orders.class.getResource("/image/btn_drink.png")));
		btnDrinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateAllDrinks();
			}
		});
		btnDrinks.setForeground(Color.WHITE);
		btnDrinks.setBackground(new Color(0, 102, 204));

		JButton btnAll = new JButton("");
		btnAll.setBorder(null);
		btnAll.setBounds(383, 418, 48, 36);
		panel_2.add(btnAll);
		btnAll.setIcon(new ImageIcon(Orders.class.getResource("/image/btn_all.png")));
		btnAll.setToolTipText("All Food");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateAllItem();
			}
		});
		btnAll.setForeground(Color.WHITE);
		btnAll.setBackground(new Color(0, 102, 204));

		JLabel lblMenuItems = new JLabel("Menu Items");
		lblMenuItems.setForeground(new Color(67, 70, 86));
		lblMenuItems.setFont(new Font("Calibri", Font.BOLD, 21));
		lblMenuItems.setBounds(10, 0, 130, 47);
		panel_2.add(lblMenuItems);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(68, 130, 236));
		panel_3.setBounds(10, 35, 149, 2);
		panel_2.add(panel_3);
		categoryComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				CategoryService c = new CategoryServiceimpl();
				HashMap<String, Integer> category = c.populateCategory();
				Integer cat_id = category.get(categoryComboBox.getSelectedItem().toString());
				populateProductByCat(cat_id);
			}
		});

		JLabel label_33 = new JLabel("");
		label_33.setIcon(new ImageIcon(Orders.class.getResource("/image/orders.jpg")));
		label_33.setBounds(0, 0, 1356, 704);
		add(label_33);

		populateAllItem();
		populateCategoryList();
		initializeSetting();

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

	private void initializeSetting() {
		SettingService settingService = new SettingServiceimpl();
		setting = settingService.getSetting();

	}

	protected void clearField() {
		txt_item.setText("");
		txt_price.setText("");
		txt_quantity.setText("");
		rdbtnDineIn.setSelected(true);

	}

	protected void populateProductOrder(int order_id2) {
		ProductOrderService pos = new ProductOrderServiceimpl();

		List<ProductOrderModel> sList = pos.getProductOrder(order_id2);

		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.setRowCount(0);

		for (ProductOrderModel s : sList) {
			model.addRow(new Object[] { s.getId(), s.getProduct_name(), s.getQuantity(), s.getPrice(),
					s.getDeliver_type() });
		}

		popupMenu = new JPopupMenu();

		menuItemRemove = new JMenuItem("Delete     ");
		menuItemRemove.addActionListener(this);

		popupMenu.add(menuItemRemove);

		// sets the popup menu for the table
		table_1.setComponentPopupMenu(popupMenu);

		table_1.addMouseListener(new TableMouseListener(table_1));

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

	protected void populateProductByCat(Integer cat_id) {
		ProductService ss = new ProductServiceimpl();
		List<ProductModel> sList = ss.getProductByCat(cat_id);

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		for (ProductModel s : sList) {
			model.addRow(new Object[] { s.getId(), s.getName(), s.getPrice(), s.getStatus() });
		}

	}

	private void populateCategoryList() {
		categoryComboBox.setModel(new DefaultComboBoxModel(new String[] { "Choose" }));
		CategoryService ss = new CategoryServiceimpl();
		List<CategoryModel> sList = ss.getCategory();
		if (sList != null) {
			for (CategoryModel s : sList) {
				categoryComboBox.addItem(s.getName());
			}
		}
	}

	protected void populateAllMain() {
		ProductOrderService ss = new ProductOrderServiceimpl();
		List<ProductModel> sList = ss.getMainOrder();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		for (ProductModel s : sList) {
			model.addRow(new Object[] { s.getId(), s.getName(), s.getPrice(), s.getStatus() });
		}

	}

	protected void populateAllDrinks() {
		/* 1. initialize service */
		DrinkService ss = new DrinkServiceimpl();
		/* 2. call service method getProduct */
		List<ProductModel> sList = ss.getDrink();

//		3.	initiazlize the table 
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

//		4.	Loop and place the data to table
		for (ProductModel s : sList) {
			model.addRow(new Object[] { s.getId(), s.getName(), s.getPrice(), s.getStatus() });
		}
	}

	private void populateAllItem() {
		// initialize service 
		ProductService ss = new ProductServiceimpl();
		// call service method getProduct
		List<ProductModel> sList = ss.getProduct();

		/* initiazlize the table */
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		/* Loop and place the data to table */
		for (ProductModel s : sList) {
			model.addRow(new Object[] { s.getId(), s.getName(), s.getPrice(), s.getStatus() });
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JMenuItem menu = (JMenuItem) event.getSource();
		if (menu == menuItemRemove) {
			removeCurrentRow();
		}
	}

	private void removeCurrentRow() {
		if (table_1.getSelectedRow() < 0) {

			JOptionPane.showMessageDialog(null, "Please select any one row");

		} else {
			int row = table_1.getSelectedRow();
			int id = (int) table_1.getModel().getValueAt(row, 0);

			ProductOrderService ls = new ProductOrderServiceimpl();
			int input = JOptionPane.showConfirmDialog(null, "Are you sure to delete this?");
			// 0=yes, 1=no, 2=cancel
			if (input == 0) {
				if (ls.deleteProductOrder(id)) {
					int selectedRow = table_1.getSelectedRow();
					model_1.removeRow(selectedRow);
					populateProductOrder(order_id);
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong");
				}
			}

		}

	}

	private void calculateGrandTotal() {
		/* initialize the variable */
		Double subtotal = 0.0;
		Double discount = 0.0;
		Double itemGst = 0.0;
		Double grandTotal = 0.0;
		Double itemPrice = 0.0;
		Double afterGst = 0.0;
		Double taxable_amount = 0.0;

		/* get the value of the price from the table */
		for (int count = 0; count < model_1.getRowCount(); count++) {
			itemPrice = Double.parseDouble(model_1.getValueAt(count, 3).toString()); // get the price and parse to Double
			subtotal += itemPrice;			// add all the price to subtotal

		}
		
		discount = (subtotal * setting.getDiscount()) / 100; 				// calculate the discount price taking the value from the Setting Service in percent
		taxable_amount = subtotal - discount;								// calculate the taxable by subtotal - discount
		itemGst += ((taxable_amount * setting.getGst()) / 100);				// calculate the gst

		grandTotal = itemGst + taxable_amount;								// calculate the grandtotal 

		
		/* set all the value to label */
		txt_subtotal.setText(subtotal + "");
		txt_discount.setText(discount + "");
		txt_gst.setText(itemGst + "");
		txt_grandtotal.setText(grandTotal + "");

		
		/* update the Order in database table */
		OrderService os = new OrderServiceimpl();
		OrderModel order = new OrderModel();
		order.setId(order_id);
		order.setTotal_price(grandTotal);
		order.setStatus("Pending");
		order.setDate(Date.getDate());
		if (!os.updateOrder(order)) {
			JOptionPane.showMessageDialog(null, "Something went wrong");
		}

	}
}
