package com.test.client.panels;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.test.client.Dashboard;

import com.test.helper.RtClickPopup;
import com.test.helper.TableMouseListener;
import com.test.models.CategoryModel;
import com.test.models.ProductModel;
import com.test.serviceimpl.CategoryServiceimpl;
import com.test.serviceimpl.ProductServiceimpl;
import com.test.services.CategoryService;
import com.test.services.ProductService;

import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Menu extends JPanel implements ActionListener {
	private JTable table;
	private JTable table_1;
	private JTextField txt_category;
	private JTextField txt_product_name;
	private JTextField txt_description;
	private JTextField txt_price;
	private JTextField txt_size;
	private JComboBox categoryComboBox;
	private JPopupMenu popupMenu, popupMenu1;
	private JMenuItem menuItemEdit;
	private JMenuItem menuItemRemove;
	private DefaultTableModel tableModel;
	private JMenuItem menuItemEdit1;
	private JMenuItem menuItemRemove1;
	private DefaultTableModel tableModel1;
	private Integer category_id = 0, product_id = 0;
	JRadioButton rdbtnAvailable, rdbtnUnavailable, rdbtnAvailable_1, rdbtnUnavailable_1;

	/**
	 * Create the panel.
	 */
	public Menu() {
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblHome = new JLabel("Menu Management");
		lblHome.setForeground(new Color(67, 70, 86));
		lblHome.setFont(new Font("Calibri", Font.BOLD, 32));
		lblHome.setBounds(10, 11, 403, 51);
		add(lblHome);
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Name", "Status" });
		tableModel1 = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Name", "Cat_id", "Description", "Size", "Price", "Status" });

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255,200));
		panel.setBorder(new CompoundBorder());
		panel.setBounds(10, 66, 871, 548);
		add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 187, 319);
		panel.add(scrollPane);

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
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(33);
		table.getColumnModel().getColumn(1).setPreferredWidth(93);
		table.getColumnModel().getColumn(2).setPreferredWidth(86);

		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(222, 51, 639, 322);
		panel.add(scrollPane_1);

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
		table_1.setModel(tableModel1);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(34);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(110);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(52);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(233);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(77);
		table_1.getColumnModel().getColumn(6).setPreferredWidth(88);
		scrollPane_1.setViewportView(table_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setRequestFocusEnabled(false);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 384, 187, 153);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCategory_1 = new JLabel("Category:");
		lblCategory_1.setBounds(10, 13, 57, 14);
		panel_1.add(lblCategory_1);

		txt_category = new JTextField();
		txt_category.setBounds(72, 10, 96, 20);
		panel_1.add(txt_category);
		txt_category.setColumns(10);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 41, 47, 14);
		panel_1.add(lblStatus);

		rdbtnAvailable = new JRadioButton("Available");
		rdbtnAvailable.setOpaque(false);
		rdbtnAvailable.setBounds(67, 37, 96, 23);
		rdbtnAvailable.setSelected(true);
		panel_1.add(rdbtnAvailable);

		rdbtnUnavailable = new JRadioButton("Unavailable");
		rdbtnUnavailable.setOpaque(false);
		rdbtnUnavailable.setBounds(66, 65, 97, 23);
		panel_1.add(rdbtnUnavailable);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnAvailable);
		bg.add(rdbtnUnavailable);

		JButton btnSave = new JButton("Add");
		btnSave.setBorder(null);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (validation()) {						// validation if true

					
					
					CategoryService ls = new CategoryServiceimpl();
					
					/* Setting the model */
					CategoryModel c = new CategoryModel();
					c.setName(txt_category.getText());
					if (rdbtnAvailable.isSelected()) {
						c.setStatus("Available");

					} else {
						c.setStatus("Unavailable");
					}
					if (ls.addCategory(c)) {					// use Service addCategory()
						JOptionPane.showMessageDialog(null, "Success !!! New Category Inserted");
						clearCategoryField();
						populateData();
						populateCategoryList();
					} else {
						JOptionPane.showMessageDialog(null, "Failed to Insert");
					}
				}
			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(new Color(0, 102, 204));
		btnSave.setBounds(103, 106, 71, 36);
		panel_1.add(btnSave);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setRequestFocusEnabled(false);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(222, 384, 638, 153);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblCategory_2 = new JLabel("Product Name");
		lblCategory_2.setBounds(10, 11, 92, 14);
		panel_2.add(lblCategory_2);

		txt_product_name = new JTextField();
		txt_product_name.setBounds(98, 8, 96, 20);
		panel_2.add(txt_product_name);
		txt_product_name.setColumns(10);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(216, 11, 82, 14);
		panel_2.add(lblDescription);

		txt_description = new JTextField();
		txt_description.setBounds(308, 8, 320, 20);
		panel_2.add(txt_description);
		txt_description.setColumns(10);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 39, 48, 14);
		panel_2.add(lblPrice);

		txt_price = new JTextField();
		txt_price.setBounds(98, 36, 96, 20);
		panel_2.add(txt_price);
		txt_price.setColumns(10);

		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(216, 39, 48, 14);
		panel_2.add(lblSize);

		txt_size = new JTextField();
		txt_size.setBounds(308, 36, 96, 20);
		panel_2.add(txt_size);
		txt_size.setColumns(10);

		JLabel lblCategory_3 = new JLabel("Category");
		lblCategory_3.setBounds(10, 71, 71, 14);
		panel_2.add(lblCategory_3);

		categoryComboBox = new JComboBox();
		categoryComboBox.setBounds(98, 67, 142, 22);

		populateCategoryList();

		panel_2.add(categoryComboBox);

		JLabel lblStatus_1 = new JLabel("Status");
		lblStatus_1.setBounds(308, 66, 48, 14);
		panel_2.add(lblStatus_1);

		rdbtnAvailable_1 = new JRadioButton("Available");
		rdbtnAvailable_1.setOpaque(false);
		rdbtnAvailable_1.setBounds(400, 62, 92, 23);
		panel_2.add(rdbtnAvailable_1);

		rdbtnUnavailable_1 = new JRadioButton("Unavailable");
		rdbtnUnavailable_1.setOpaque(false);
		rdbtnUnavailable_1.setBounds(509, 62, 109, 23);
		panel_2.add(rdbtnUnavailable_1);

		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rdbtnAvailable_1);
		bg1.add(rdbtnUnavailable_1);
		rdbtnAvailable_1.setSelected(true);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBorder(null);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (product_validation()) {
					CategoryService c = new CategoryServiceimpl();
					HashMap<String, Integer> category = c.populateCategory();
					Integer cs = category.get(categoryComboBox.getSelectedItem().toString());

					ProductModel product = new ProductModel();
					product.setName(txt_product_name.getText());
					product.setPrice(Double.parseDouble(txt_price.getText()));
					product.setCategory_id(cs);
					product.setDescription(txt_description.getText());
					product.setSize(txt_size.getText());
					if (rdbtnAvailable_1.isSelected()) {
						product.setStatus("Available");

					} else {
						product.setStatus("Unavailable");
					}
					ProductService ls = new ProductServiceimpl();
					if (ls.addProduct(product)) {
						JOptionPane.showMessageDialog(null, "Success !!! New Product has been added");
						clearProductField();
						populateProductData();

					}

				}
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(0, 102, 204));
		btnAdd.setBounds(557, 106, 71, 36);
		panel_2.add(btnAdd);

		JButton btnUpdateProduct = new JButton("Update");
		btnUpdateProduct.setBorder(null);
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (product_id != 0) {
					if (product_validation()) {					// validation 
						
						
						CategoryService c = new CategoryServiceimpl();
						HashMap<String, Integer> category = c.populateCategory();
						Integer cs = category.get(categoryComboBox.getSelectedItem().toString());
						
						
						/* Setting the Productmodel */ 
						ProductModel product = new ProductModel();
						product.setId(product_id);
						product.setName(txt_product_name.getText());
						product.setPrice(Double.parseDouble(txt_price.getText()));
						product.setCategory_id(cs);
						product.setDescription(txt_description.getText());
						product.setSize(txt_size.getText());
						if (rdbtnAvailable_1.isSelected()) {
							product.setStatus("Available");

						} else {
							product.setStatus("Unavailable");
						}
						ProductService ls = new ProductServiceimpl();
						if (ls.updateProduct(product)) {				// call updateProduct service 
							JOptionPane.showMessageDialog(null, "Update Successfully!!! ");
							populateProductData();
							clearProductField();
						} else {
							JOptionPane.showMessageDialog(null, "Failed to Update");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Right click on row and Edit first");
				}
			}
		});
		btnUpdateProduct.setForeground(Color.WHITE);
		btnUpdateProduct.setBackground(new Color(255, 124, 64));
		btnUpdateProduct.setBounds(464, 106, 83, 36);
		panel_2.add(btnUpdateProduct);

		JButton btnUpdateCategory = new JButton("Update");
		btnUpdateCategory.setBorder(null);
		btnUpdateCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (category_id != 0) {
					if (validation()) {					// validation 
						
						CategoryService ls = new CategoryServiceimpl();
						
						/* Setting the Categorymodel */
						CategoryModel c = new CategoryModel();
						c.setName(txt_category.getText());
						c.setId(category_id);
						if (rdbtnAvailable.isSelected()) {
							c.setStatus("Available");

						} else {
							c.setStatus("Unavailable");
						}
						if (ls.updateCategory(c)) {				// call the service updateCategory
							JOptionPane.showMessageDialog(null, "Update Successfully!!! ");
							clearCategoryField();
							populateData();
							populateCategoryList();

						} else {
							JOptionPane.showMessageDialog(null, "Failed to Update");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Right click on selected row and Edit First");
				}
			}
		});
		btnUpdateCategory.setForeground(Color.WHITE);
		btnUpdateCategory.setBackground(new Color(255, 124, 64));
		btnUpdateCategory.setBounds(10, 106, 83, 36);
		panel_1.add(btnUpdateCategory);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(68, 130, 236));
		panel_3.setBounds(10, 35, 149, 2);
		panel.add(panel_3);

		JLabel lblCategories = new JLabel("Categories");
		lblCategories.setForeground(new Color(67, 70, 86));
		lblCategories.setFont(new Font("Calibri", Font.BOLD, 21));
		lblCategories.setBounds(10, 0, 130, 47);
		panel.add(lblCategories);

		JLabel lblMenuItems = new JLabel("Menu Items");
		lblMenuItems.setForeground(new Color(67, 70, 86));
		lblMenuItems.setFont(new Font("Calibri", Font.BOLD, 21));
		lblMenuItems.setBounds(222, 0, 130, 47);
		panel.add(lblMenuItems);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(68, 130, 236));
		panel_4.setBounds(222, 35, 149, 2);
		panel.add(panel_4);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Menu.class.getResource("/image/background2.jpg")));
		label.setBounds(0, 0, 1356, 704);
		add(label);

		populateData();
		populateProductData();
	}

	protected void clearProductField() {
		product_id = 0;
		txt_product_name.setText("");
		txt_description.setText("");
		txt_price.setText("");
		txt_size.setText("");
		rdbtnAvailable_1.setSelected(true);

	}

	protected void clearCategoryField() {
		category_id = 0;
		txt_category.setText("");
		rdbtnAvailable.setSelected(true);
	}

	protected boolean product_validation() {

		if (txt_product_name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Product Name");
			return false;
		}
		if (txt_price.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Product Price");
			return false;
		}
		try {
			float product_price = Float.parseFloat(txt_price.getText());

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please actual price in number");
			return false;
		}

		String category_name = categoryComboBox.getSelectedItem().toString();
		if (category_name.equals("Choose") || category_name.equals("")) {
			JOptionPane.showMessageDialog(null, "Please Choose Category Name");
			return false;
		}

		return true;
	}

	protected boolean validation() {
		if (txt_category.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Category Name");
			return false;
		}
		return true;
	}

	private List<CategoryModel> getCategory() {
		CategoryService ss = new CategoryServiceimpl();
		List<CategoryModel> sList = ss.getCategory();

		return sList;

	}

	private void populateData() {
		CategoryService ss = new CategoryServiceimpl();
		List<CategoryModel> sList = ss.getCategory();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		for (CategoryModel s : sList) {
			model.addRow(new Object[] { s.getId(), s.getName(), s.getStatus() });
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

	private void populateProductData() {

		ProductService ss = new ProductServiceimpl();
		List<ProductModel> sList = ss.getProduct();

		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.setRowCount(0);

		for (ProductModel s : sList) {
			model.addRow(new Object[] { s.getId(), s.getName(), s.getCategory_id(), s.getDescription(), s.getSize(),
					s.getPrice(), s.getStatus() });
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

	private void populateCategoryList() {
		categoryComboBox.setModel(new DefaultComboBoxModel(new String[] { "Choose" }));
		List<CategoryModel> sList = getCategory();
		if (sList != null) {
			for (CategoryModel s : sList) {
				categoryComboBox.addItem(s.getName());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JMenuItem menu = (JMenuItem) event.getSource();
		if (menu == menuItemEdit) {
			editrow();
		} else if (menu == menuItemRemove) {
			removeCurrentRow();
		} else if (menu == menuItemEdit1) {
			editProduct();
		} else if (menu == menuItemRemove1) {
			deleteProduct();
		}
	}

	private void deleteProduct() {
		if (table_1.getSelectedRow() < 0) {

			JOptionPane.showMessageDialog(null, "Please select any one row");

		} else {
			// get selected row
			int row = table_1.getSelectedRow();
			int id = (int) table_1.getModel().getValueAt(row, 0); // extract id from the table
			
			ProductService ls = new ProductServiceimpl();
			int input = JOptionPane.showConfirmDialog(null, "Are you sure?"); // confirm to delete
			// 0=yes, 1=no, 2=cancel
			if (input == 0) {
				if (ls.deleteProduct(id)) {			// if yes then delte
					int selectedRow = table_1.getSelectedRow();
					tableModel1.removeRow(selectedRow);
					populateProductData();
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong");
				}
			}

		}

	}

	private void editProduct() {
		int row = table_1.getSelectedRow();
		int id = (int) table_1.getModel().getValueAt(row, 0);
		displayProductData(id);
	}

	private void editrow() {

		int row = table.getSelectedRow();
		int id = (int) table.getModel().getValueAt(row, 0);

		displayCategoryData(id);
	}

	private void removeCurrentRow() {
		if (table.getSelectedRow() < 0) {

			JOptionPane.showMessageDialog(null, "Please select any one row");

		} else {
			int row = table.getSelectedRow();
			int id = (int) table.getModel().getValueAt(row, 0);

			if (id == 2 || id == 1 ) {				
				JOptionPane.showMessageDialog(null, "Sorry it can't be delete");
			} else {
				CategoryService ls = new CategoryServiceimpl();
				int input = JOptionPane.showConfirmDialog(null,
						"Are you sure? This will delete all product of this category");
				// 0=yes, 1=no, 2=cancel
				if (input == 0) {
					if (ls.deleteCategory(id)) {
						int selectedRow = table.getSelectedRow();
						tableModel.removeRow(selectedRow);
						populateProductData();
						populateCategoryList();
					} else {
						JOptionPane.showMessageDialog(null, "Something went wrong");
					}
				}
			}

		}

	}

	public void displayCategoryData(int id) {
		this.category_id = id;
		CategoryService ss = new CategoryServiceimpl();
		CategoryModel s = ss.getById(id);
		txt_category.setText(s.getName());
		if (s.getStatus().equals("Available")) {
			rdbtnAvailable.setSelected(true);
		} else {
			rdbtnUnavailable.setSelected(true);
		}

	}

	private void displayProductData(int id) {
		this.product_id = id;
		ProductService pp = new ProductServiceimpl();
		ProductModel p = pp.getById(id);

		txt_product_name.setText(p.getName());
		txt_price.setText(p.getPrice() + "");
		txt_description.setText(p.getDescription());
		txt_size.setText(p.getSize());

		CategoryService ss = new CategoryServiceimpl();
		CategoryModel s = ss.getById(p.getCategory_id());
		String category_name = s.getName();

		categoryComboBox.setSelectedItem(category_name);
		if (p.getStatus().equals("Available")) {
			rdbtnAvailable_1.setSelected(true);
		} else {
			rdbtnUnavailable_1.setSelected(true);
		}

	}

}
