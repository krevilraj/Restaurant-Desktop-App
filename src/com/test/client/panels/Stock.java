package com.test.client.panels;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.test.client.Dashboard;
import com.test.helper.TableMouseListener;
import com.test.models.CategoryModel;
import com.test.models.StockModel;
import com.test.serviceimpl.CategoryServiceimpl;
import com.test.serviceimpl.StockServiceimpl;
import com.test.services.CategoryService;
import com.test.services.StockService;

import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Stock extends JPanel implements ActionListener {
	private JTable table;
	private JTable table_1;
	private JTextField txt_product_name1;
	private JTextField txt_quantity1;
	private JTextField txt_unit1;
	private JPopupMenu popupMenu, popupMenu1;
	private JMenuItem menuItemEdit;
	private JMenuItem menuItemRemove;
	private DefaultTableModel tableModel;
	private JMenuItem menuItemEdit1;
	private JMenuItem menuItemRemove1;
	private DefaultTableModel tableModel1;
	private Integer category_id, product_id;
	private JTextField txt_product_name;
	private JTextField txt_quantity;
	private JTextField txt_unit;
	private Integer food_id = 0, drink_id = 0;

	/**
	 * Create the panel.
	 */
	
	/* Constructor of Stock */
	public Stock() {
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblHome = new JLabel("Stock Management");
		lblHome.setFont(new Font("Calibri", Font.BOLD, 32));
		lblHome.setBounds(21, 11, 403, 51);
		add(lblHome);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 115, 386, 339);
		add(scrollPane);

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
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Name", "Quantity" });
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setMaxWidth(33);
		table.getColumnModel().getColumn(1).setPreferredWidth(93);
		table.getColumnModel().getColumn(2).setPreferredWidth(86);

		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(465, 115, 406, 339);
		add(scrollPane_1);

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
		tableModel1 = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Name", "Quantity" });
		table_1.setModel(tableModel1);
		table_1.getColumnModel().getColumn(0).setMaxWidth(33);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(93);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(86);
		scrollPane_1.setViewportView(table_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255,200));
		panel.setBorder(new CompoundBorder());
		panel.setBounds(21, 62, 860, 550);
		add(panel);
		panel.setLayout(null);

		ButtonGroup bg = new ButtonGroup();

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(444, 404, 406, 135);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblCategory_2 = new JLabel("Drink Name");
		lblCategory_2.setBounds(20, 23, 92, 14);
		panel_2.add(lblCategory_2);

		txt_product_name1 = new JTextField();
		txt_product_name1.setBounds(98, 18, 119, 20);
		panel_2.add(txt_product_name1);
		txt_product_name1.setColumns(10);

		JLabel lblPrice = new JLabel("Quantity");
		lblPrice.setBounds(20, 60, 48, 14);
		panel_2.add(lblPrice);

		txt_quantity1 = new JTextField();
		txt_quantity1.setBounds(98, 56, 119, 20);
		panel_2.add(txt_quantity1);
		txt_quantity1.setColumns(10);

		JLabel lblSize = new JLabel("Unit");
		lblSize.setBounds(20, 97, 48, 14);
		panel_2.add(lblSize);

		txt_unit1 = new JTextField();
		txt_unit1.setBounds(98, 94, 119, 20);
		panel_2.add(txt_unit1);
		txt_unit1.setColumns(10);

//		populateCategoryList();

		ButtonGroup bg1 = new ButtonGroup();

		JButton btn_add_drink = new JButton("Add");
		btn_add_drink.setBorder(null);
		btn_add_drink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (drink_validation()) {
					StockService ls = new StockServiceimpl();
					StockModel c = new StockModel();
					c.setProduct_name(txt_product_name1.getText());
					c.setQuantity(Integer.parseInt(txt_quantity1.getText()));
					c.setUnit(txt_unit1.getText());
					c.setFood_type("Drink");

					if (ls.addStock(c)) {
						JOptionPane.showMessageDialog(null, "New Item has been added !!! ");
						clearDrinkField();
						populateDrinkData();

					} else {
						JOptionPane.showMessageDialog(null, "Failed to Update");
					}

				}
			}
		});
		btn_add_drink.setForeground(Color.WHITE);
		btn_add_drink.setBackground(new Color(0, 102, 204));
		btn_add_drink.setBounds(299, 78, 85, 36);
		panel_2.add(btn_add_drink);

		JButton btn_update_drink = new JButton("Update");
		btn_update_drink.setBorder(null);
		btn_update_drink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (drink_id == 0) {
					JOptionPane.showMessageDialog(null, "Right click on selected row and Edit First ");
				} else {
					if (drink_validation()) {
						StockService ls = new StockServiceimpl();
						StockModel c = new StockModel();
						c.setProduct_name(txt_product_name1.getText());
						c.setQuantity(Integer.parseInt(txt_quantity1.getText()));
						c.setUnit(txt_unit1.getText());
						c.setFood_type("Drink");
						c.setId(drink_id);

						if (ls.updateStock(c)) {
							JOptionPane.showMessageDialog(null, "Update Successfully!!! ");
							clearDrinkField();
							populateDrinkData();

						} else {
							JOptionPane.showMessageDialog(null, "Failed to Update");
						}
					}
				}
			}
		});
		btn_update_drink.setForeground(Color.WHITE);
		btn_update_drink.setBackground(new Color(255, 124, 64));
		btn_update_drink.setBounds(299, 21, 83, 36);
		panel_2.add(btn_update_drink);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(12, 404, 386, 135);
		panel.add(panel_1);

		JLabel lblFoodName = new JLabel("Food Name");
		lblFoodName.setBounds(22, 23, 92, 14);
		panel_1.add(lblFoodName);

		txt_product_name = new JTextField();
		txt_product_name.setColumns(10);
		txt_product_name.setBounds(98, 18, 119, 20);
		panel_1.add(txt_product_name);

		JLabel label_1 = new JLabel("Quantity");
		label_1.setBounds(22, 60, 48, 14);
		panel_1.add(label_1);

		txt_quantity = new JTextField();
		txt_quantity.setColumns(10);
		txt_quantity.setBounds(98, 56, 119, 20);
		panel_1.add(txt_quantity);

		JLabel label_2 = new JLabel("Unit");
		label_2.setBounds(22, 97, 48, 14);
		panel_1.add(label_2);

		txt_unit = new JTextField();
		txt_unit.setColumns(10);
		txt_unit.setBounds(98, 94, 119, 20);
		panel_1.add(txt_unit);

		JButton btn_add_food = new JButton("Add");
		btn_add_food.setBorder(null);
		btn_add_food.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (food_validation()) {
					StockService ls = new StockServiceimpl();
					StockModel c = new StockModel();
					c.setProduct_name(txt_product_name.getText());
					c.setQuantity(Integer.parseInt(txt_quantity.getText()));
					c.setUnit(txt_unit.getText());
					c.setFood_type("Food");

					if (ls.addStock(c)) {
						JOptionPane.showMessageDialog(null, "New Item has been added !!! ");
						clearFoodField();
						populateFoodData();

					} else {
						JOptionPane.showMessageDialog(null, "Failed to Update");
					}
				}
			}
		});
		btn_add_food.setForeground(Color.WHITE);
		btn_add_food.setBackground(new Color(0, 102, 204));
		btn_add_food.setBounds(279, 78, 83, 36);
		panel_1.add(btn_add_food);

		JButton btn_update_food = new JButton("Update");
		btn_update_food.setBorder(null);
		btn_update_food.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (food_id == 0) {
					JOptionPane.showMessageDialog(null, "Right click on selected row and Edit First ");
				} else {
					if (food_validation()) {
						StockService ls = new StockServiceimpl();
						StockModel c = new StockModel();
						c.setProduct_name(txt_product_name.getText());
						c.setQuantity(Integer.parseInt(txt_quantity.getText()));
						c.setUnit(txt_unit.getText());
						c.setFood_type("Food");
						c.setId(food_id);

						if (ls.updateStock(c)) {
							JOptionPane.showMessageDialog(null, "Update Successfully!!! ");
							clearFoodField();
							populateFoodData();

						} else {
							JOptionPane.showMessageDialog(null, "Failed to Update");
						}
					}
				}
			}
		});
		btn_update_food.setForeground(Color.WHITE);
		btn_update_food.setBackground(new Color(255, 124, 64));
		btn_update_food.setBounds(279, 21, 83, 36);
		panel_1.add(btn_update_food);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(68, 130, 236));
		panel_3.setBounds(12, 46, 149, 2);
		panel.add(panel_3);
		
		JLabel lblFood = new JLabel("Foods");
		lblFood.setForeground(new Color(67, 70, 86));
		lblFood.setFont(new Font("Calibri", Font.BOLD, 21));
		lblFood.setBounds(12, 11, 130, 47);
		panel.add(lblFood);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(68, 130, 236));
		panel_4.setBounds(444, 46, 149, 2);
		panel.add(panel_4);
		
		JLabel label_3 = new JLabel("Drinks");
		label_3.setForeground(new Color(67, 70, 86));
		label_3.setFont(new Font("Calibri", Font.BOLD, 21));
		label_3.setBounds(444, 11, 130, 47);
		panel.add(label_3);
		
		JLabel label_33 = new JLabel("");
		label_33.setIcon(new ImageIcon(Drinks.class.getResource("/image/background2.jpg")));
		label_33.setBounds(0, 0, 1356, 704);
		add(label_33);

		populateFoodData();
		populateDrinkData();
	}

	protected void clearFoodField() {
		food_id = 0;
		txt_product_name.setText("");
		txt_quantity.setText("");
		txt_unit.setText("");

	}

	protected void clearDrinkField() {
		drink_id = 0;
		txt_product_name1.setText("");
		txt_quantity1.setText("");
		txt_unit1.setText("");

	}

	protected boolean drink_validation() {

		if (txt_product_name1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Drink Name");
			return false;
		}
		if (txt_unit1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Unit");
			return false;
		}
		if (txt_quantity1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Quantity in number");
			return false;
		}
		try {
			Integer product_price = Integer.parseInt(txt_quantity1.getText());

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please actual Quantity in number");
			return false;
		}

		return true;
	}

	protected boolean food_validation() {

		if (txt_product_name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Drink Name");
			return false;
		}
		if (txt_unit.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Unit");
			return false;
		}
		if (txt_quantity.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Quantity in number");
			return false;
		}
		try {
			Integer product_price = Integer.parseInt(txt_quantity.getText());

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please actual Quantity in number");
			return false;
		}

		return true;
	}

	private void populateFoodData() {
		
		/*  initialize service */
		StockService ss = new StockServiceimpl();
		
		/*  call service method getStock */
		List<StockModel> sList = ss.getStock("Food");

		/*  initiazlize the table */
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		/*  Loop and place the data to table */
		for (StockModel s : sList) {
			model.addRow(new Object[] { s.getId(), s.getProduct_name(), s.getQuantity() + " " + s.getUnit() });
		}

		popupMenu = new JPopupMenu();
		menuItemEdit = new JMenuItem("Edit        ");
		menuItemRemove = new JMenuItem("Delete     ");

		menuItemEdit.addActionListener(this);
		menuItemRemove.addActionListener(this);

		popupMenu.add(menuItemEdit);
		popupMenu.add(menuItemRemove);

		// sets the popup menu for the table
		table.setComponentPopupMenu(popupMenu);

		table.addMouseListener(new TableMouseListener(table));

	}

	private void populateDrinkData() {
		/*  initialize StockService */
		StockService ss = new StockServiceimpl();
		/*  call service method getProduct */
		List<StockModel> sList = ss.getStock("Drink");

		/*  initiazlize the table */
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.setRowCount(0);

		/* Loop and place the data to table */
		for (StockModel s : sList) {
			model.addRow(new Object[] { s.getId(), s.getProduct_name(), s.getQuantity() + " " + s.getUnit() });
		}

		popupMenu1 = new JPopupMenu();
		menuItemEdit1 = new JMenuItem("Edit        ");
		menuItemRemove1 = new JMenuItem("Delete     ");

		menuItemEdit1.addActionListener(this);
		menuItemRemove1.addActionListener(this);

		popupMenu1.add(menuItemEdit1);
		popupMenu1.add(menuItemRemove1);

		// sets the popup menu for the table
		table_1.setComponentPopupMenu(popupMenu1);

		table_1.addMouseListener(new TableMouseListener(table_1));

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JMenuItem menu = (JMenuItem) event.getSource();
		if (menu == menuItemEdit) {
			editFood();
		} else if (menu == menuItemRemove) {
			deleteFood();
		} else if (menu == menuItemEdit1) {
			editDrink();
		} else if (menu == menuItemRemove1) {
			deleteDrink();
		}
	}

	private void deleteFood() {
		if (table.getSelectedRow() < 0) {

			JOptionPane.showMessageDialog(null, "Please select any one row");

		} else {
			int row = table.getSelectedRow();
			int id = (int) table.getModel().getValueAt(row, 0);
			StockService ls = new StockServiceimpl();
			int input = JOptionPane.showConfirmDialog(null, "Are you sure?");
			// 0=yes, 1=no, 2=cancel
			if (input == 0) {
				if (ls.deleteStock(id)) {
					int selectedRow = table.getSelectedRow();
					tableModel.removeRow(selectedRow);
					populateFoodData();
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong");
				}
			}

		}

	}

	private void deleteDrink() {
		if (table_1.getSelectedRow() < 0) {

			JOptionPane.showMessageDialog(null, "Please select any one row");

		} else {
			int row = table_1.getSelectedRow();
			int id = (int) table_1.getModel().getValueAt(row, 0);
			StockService ls = new StockServiceimpl();
			int input = JOptionPane.showConfirmDialog(null, "Are you sure?");
			// 0=yes, 1=no, 2=cancel
			if (input == 0) {
				if (ls.deleteStock(id)) {
					int selectedRow = table_1.getSelectedRow();
					tableModel1.removeRow(selectedRow);
					populateDrinkData();
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong");
				}
			}

		}

	}

	private void editFood() {
		int row = table.getSelectedRow();
		int id = (int) table.getModel().getValueAt(row, 0);
		displayFoodData(id);
	}

	private void editDrink() {

		int row = table_1.getSelectedRow();
		int id = (int) table_1.getModel().getValueAt(row, 0);

		displayDrinkData(id);
	}

	public void displayFoodData(int id) {
		this.food_id = id;
		StockService ss = new StockServiceimpl();
		StockModel s = ss.getById(id);
		txt_product_name.setText(s.getProduct_name());
		txt_quantity.setText(s.getQuantity() + "");
		txt_unit.setText(s.getUnit());

	}

	public void displayDrinkData(int id) {
		this.drink_id = id;
		StockService ss = new StockServiceimpl();
		StockModel s = ss.getById(id);
		txt_product_name1.setText(s.getProduct_name());
		txt_quantity1.setText(s.getQuantity() + "");
		txt_unit1.setText(s.getUnit());

	}

}
