package com.test.client.panels;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import com.test.helper.TableMouseListener;
import com.test.models.CategoryModel;
import com.test.models.ProductModel;
import com.test.serviceimpl.CategoryServiceimpl;
import com.test.serviceimpl.DrinkServiceimpl;
import com.test.serviceimpl.ProductServiceimpl;
import com.test.services.CategoryService;
import com.test.services.DrinkService;
import com.test.services.ProductService;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class Drinks extends JPanel implements ActionListener {
	private JTextField txt_drink_name;
	private JTextField txt_price;
	private JTable table;
	private JPopupMenu popupMenu;
	private JMenuItem menuItemEdit;
	private JMenuItem menuItemRemove;
	private DefaultTableModel tableModel;
	private Integer drink_id = 0;
	private JTextArea txt_description;
	private JRadioButton rbtnAvailable, rbtnUnAvailable;

	/**
	 * Create the panel.
	 */
	public Drinks() {
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblHome = new JLabel("Drinks Management");
		lblHome.setForeground(new Color(67, 70, 86));
		lblHome.setFont(new Font("Calibri", Font.BOLD, 32));
		lblHome.setBounds(21, 11, 403, 51);
		add(lblHome);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 112, 835, 316);
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
		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Name", "Price", "Description", "Status" });
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(32);
		table.getColumnModel().getColumn(0).setMaxWidth(32);
		table.getColumnModel().getColumn(1).setPreferredWidth(113);
		table.getColumnModel().getColumn(3).setPreferredWidth(226);
		table.getColumnModel().getColumn(4).setPreferredWidth(101);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(31, 439, 835, 149);
		add(panel);

		JLabel label = new JLabel("Product Name");
		label.setBounds(21, 16, 92, 14);
		panel.add(label);

		txt_drink_name = new JTextField();
		txt_drink_name.setColumns(10);
		txt_drink_name.setBounds(109, 13, 169, 20);
		panel.add(txt_drink_name);

		JLabel label_1 = new JLabel("Description");
		label_1.setBounds(309, 16, 82, 14);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Price");
		label_2.setBounds(21, 53, 48, 14);
		panel.add(label_2);

		txt_price = new JTextField();
		txt_price.setColumns(10);
		txt_price.setBounds(109, 50, 169, 20);
		panel.add(txt_price);

		JLabel label_5 = new JLabel("Status");
		label_5.setBounds(21, 92, 48, 14);
		panel.add(label_5);

		rbtnAvailable = new JRadioButton("Available");
		rbtnAvailable.setBackground(Color.WHITE);
		rbtnAvailable.setSelected(true);
		rbtnAvailable.setBounds(113, 88, 92, 23);
		panel.add(rbtnAvailable);

		JRadioButton rbtnUnAvailable = new JRadioButton("Unavailable");
		rbtnUnAvailable.setBackground(Color.WHITE);
		rbtnUnAvailable.setBounds(207, 88, 109, 23);
		panel.add(rbtnUnAvailable);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rbtnAvailable);
		bg.add(rbtnUnAvailable);

		JButton btn_add = new JButton("Add");
		btn_add.setBorder(null);
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (drink_validation()) {				// first validation

						//setting MOdel
					ProductModel product = new ProductModel();
					product.setName(txt_drink_name.getText());
					product.setPrice(Double.parseDouble(txt_price.getText()));

					product.setDescription(txt_description.getText());
					if (rbtnAvailable.isSelected()) {
						product.setStatus("Available");

					} else {
						product.setStatus("Unavailable");
					}
					DrinkService ls = new DrinkServiceimpl();
					if (ls.addDrink(product)) {						// use addDrink service model
						JOptionPane.showMessageDialog(null, "Success !!! New Drink has been added");
						clearProductField();
						populateDrinks();

					}

				}
			}
		});
		btn_add.setForeground(Color.WHITE);
		btn_add.setBackground(new Color(0, 102, 204));
		btn_add.setBounds(603, 102, 92, 36);
		panel.add(btn_add);

		JButton btn_update = new JButton("Update");
		btn_update.setBorder(null);
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (drink_id == 0) {
					JOptionPane.showMessageDialog(null, "Select one row and edit");
				} else {
					if (drink_validation()) {

						ProductModel product = new ProductModel();
						product.setId(drink_id);
						product.setName(txt_drink_name.getText());
						product.setPrice(Double.parseDouble(txt_price.getText()));

						product.setDescription(txt_description.getText());

						if (rbtnAvailable.isSelected()) {
							product.setStatus("Available");

						} else {
							product.setStatus("Unavailable");
						}
						DrinkService ls = new DrinkServiceimpl();
						if (ls.updateDrink(product)) {
							JOptionPane.showMessageDialog(null, "Update Successfully!!! ");
							populateDrinks();
							clearProductField();
						} else {
							JOptionPane.showMessageDialog(null, "Failed to Update");
						}
					}
				}
			}
		});
		btn_update.setForeground(Color.WHITE);
		btn_update.setBackground(new Color(255, 124, 64));
		btn_update.setBounds(510, 102, 83, 36);
		panel.add(btn_update);

		txt_description = new JTextArea();
		txt_description.setBackground(UIManager.getColor("ComboBox.buttonBackground"));
		txt_description.setBounds(388, 11, 307, 75);
		panel.add(txt_description);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255,200));
		panel_1.setBounds(21, 62, 858, 539);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDrinks = new JLabel("Drinks");
		lblDrinks.setForeground(new Color(67, 70, 86));
		lblDrinks.setFont(new Font("Calibri", Font.BOLD, 21));
		lblDrinks.setBounds(10, 0, 130, 47);
		panel_1.add(lblDrinks);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(68, 130, 236));
		panel_2.setBounds(10, 35, 149, 2);
		panel_1.add(panel_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(Drinks.class.getResource("/image/background2.jpg")));
		label_3.setBounds(0, 0, 1356, 704);
		add(label_3);
		populateDrinks();
	}

	protected boolean drink_validation() {
		if (txt_drink_name.getText().equals("")) {
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

		return true;
	}

	protected void clearProductField() {
		drink_id = 0;
		txt_drink_name.setText("");
		txt_price.setText("");
		txt_description.setText("");
		rbtnAvailable.setSelected(true);

	}

	private void populateDrinks() {
		DrinkService ss = new DrinkServiceimpl();
		List<ProductModel> sList = ss.getDrink();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		for (ProductModel s : sList) {
			model.addRow(new Object[] { s.getId(), s.getName(), s.getPrice(), s.getDescription(), s.getStatus() });
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

	@Override
	public void actionPerformed(ActionEvent event) {
		JMenuItem menu = (JMenuItem) event.getSource();
		if (menu == menuItemEdit) {
			editDrink();
		} else if (menu == menuItemRemove) {
			deleteDrink();
		}
	}

	private void editDrink() {
		int row = table.getSelectedRow();
		int id = (int) table.getModel().getValueAt(row, 0);
		displayDrinkData(id);
	}

	private void displayDrinkData(int id) {
		this.drink_id = id;
		DrinkService pp = new DrinkServiceimpl();
		ProductModel p = pp.getById(id);

		txt_drink_name.setText(p.getName());
		txt_price.setText(p.getPrice()+"");
		txt_description.setText(p.getDescription());

		if (p.getStatus().equals("Available")) {
			rbtnAvailable.setSelected(true);
		} else {
			rbtnUnAvailable.setSelected(true);
		}

	}

	private void deleteDrink() {
		if (table.getSelectedRow() < 0) {

			JOptionPane.showMessageDialog(null, "Please select any one row");

		} else {
			int row = table.getSelectedRow();
			int id = (int) table.getModel().getValueAt(row, 0);
			DrinkService ls = new DrinkServiceimpl();
			int input = JOptionPane.showConfirmDialog(null, "Are you sure?");
			// 0=yes, 1=no, 2=cancel
			if (input == 0) {
				if (ls.deleteDrink(id)) {
					int selectedRow = table.getSelectedRow();
					tableModel.removeRow(selectedRow);
					populateDrinks();
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong");
				}
			}

		}

	}

}
