package com.test.client.panels;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.test.helper.TableMouseListener;
import com.test.models.CategoryModel;
import com.test.models.ProductModel;
import com.test.models.UserModel;
import com.test.serviceimpl.CategoryServiceimpl;
import com.test.serviceimpl.ProductServiceimpl;
import com.test.serviceimpl.UserServiceimpl;
import com.test.services.CategoryService;
import com.test.services.ProductService;
import com.test.services.UserService;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class Employee extends JPanel implements ActionListener {
	private JTable table;
	private JTextField txt_username;
	private JTextField txt_phone;
	private JTextField txt_tax_file_number;
	private JTextField txt_firstname;
	private JTextField txt_lastname;
	private JPopupMenu popupMenu;
	private JMenuItem menuItemEdit;
	private JMenuItem menuItemRemove;
	private DefaultTableModel tableModel;
	private Integer user_id;
	private JTextField txt_email;
	private JTextArea txt_address;
	private JDateChooser dateChooser;
	private JComboBox userTypeComboBox;
	

	/**
	 * Create the panel.
	 */
	
	/* the constructor of the Employee */ 
	public Employee() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblHome = new JLabel("Employee Management");
		lblHome.setFont(new Font("Calibri", Font.BOLD, 32));
		lblHome.setBounds(21, 11, 403, 51);
		add(lblHome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 110, 840, 314);
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
		tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Username", "Firstname", "Lastname", "Email", "DOB", "Phone", "Tax No.", "Address", "User type"
				}
			);
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(29);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new LineBorder(Color.DARK_GRAY));
		panel.setBounds(31, 435, 840, 177);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(680, 11, 144, 155);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		
		
		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(9, 24, 76, 14);
		panel.add(lblUsername);
		
		txt_username = new JTextField();
		txt_username.setBounds(94, 21, 125, 20);
		panel.add(txt_username);
		txt_username.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(9, 67, 76, 14);
		panel.add(lblPhone);
		
		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(94, 64, 125, 20);
		panel.add(txt_phone);
		
		JLabel lblTaxFileNumber = new JLabel("Tax Number");
		lblTaxFileNumber.setBounds(9, 111, 76, 14);
		panel.add(lblTaxFileNumber);
		
		txt_tax_file_number = new JTextField();
		txt_tax_file_number.setColumns(10);
		txt_tax_file_number.setBounds(94, 108, 125, 20);
		panel.add(txt_tax_file_number);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setBounds(242, 24, 76, 14);
		panel.add(lblFirstname);
		
		txt_firstname = new JTextField();
		txt_firstname.setColumns(10);
		txt_firstname.setBounds(328, 21, 125, 20);
		panel.add(txt_firstname);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setBounds(463, 24, 76, 14);
		panel.add(lblLastname);
		
		txt_lastname = new JTextField();
		txt_lastname.setColumns(10);
		txt_lastname.setBounds(549, 21, 121, 20);
		panel.add(txt_lastname);
		
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(328, 64, 125, 20);
		dateChooser.setEnabled(false);
		dateChooser.getCalendarButton().setEnabled(true);		
		panel.add(dateChooser);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(242, 67, 76, 14);
		panel.add(lblDateOfBirth);
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setBounds(463, 67, 76, 14);
		panel.add(lblUserType);
		
		userTypeComboBox = new JComboBox();
		userTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Employee", "Manager"}));
		userTypeComboBox.setBounds(549, 63, 121, 22);
		panel.add(userTypeComboBox);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(242, 111, 76, 14);
		panel.add(lblAddress);
		
		txt_address = new JTextArea();
		txt_address.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_address.setBounds(328, 106, 342, 60);
		panel.add(txt_address);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(9, 152, 76, 14);
		panel.add(lblEmail);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(94, 146, 125, 20);
		
		JButton btn_add = new JButton("Add");
		btn_add.setBorder(null);
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userValidation()) {     					// call the userValidation method if the user typed all the required field or not
					
					/* prepare the usermodel to insert */
					UserModel user = new UserModel();
					user.setUsername(txt_username.getText());
					user.setFirstname(txt_firstname.getText());
					user.setLastname(txt_lastname.getText());
					user.setEmail(txt_email.getText());
					user.setPhone(txt_phone.getText());
					user.setAddress(txt_address.getText());
					user.setTax_file_number(txt_tax_file_number.getText());
					user.setUser_type(userTypeComboBox.getSelectedItem().toString());
					
					
					
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					String date_of_birth = df.format(dateChooser.getDate());
					user.setDate_of_birth(date_of_birth);	
					
					/* Use UserSericeImpl to add user using addUser method of Service */
					UserService ls = new UserServiceimpl();
					if (ls.addUser(user)) {
						JOptionPane.showMessageDialog(null, "Success !!! New Employee has been Added");
						
						// clear all the field after add set all the field default
						clearProductField();
						/* populate all the user to table */
						populateUserData();

					}else {
						JOptionPane.showMessageDialog(null, "Something went wrong");
					}

				}
			}
		});
		btn_add.setForeground(Color.WHITE);
		btn_add.setBackground(new Color(0, 102, 204));
		btn_add.setBounds(10, 11, 118, 36);
		panel_1.add(btn_add);
		
		JButton btn_update = new JButton("Update");
		btn_update.setBorder(null);
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userValidation()) {    			// if the validation return true 
					
					/* prepare the user model to update */
					UserModel user = new UserModel();
					user.setId(user_id);								// set id
					user.setUsername(txt_username.getText());			// set username
					user.setFirstname(txt_firstname.getText());			// set firstname
					user.setLastname(txt_lastname.getText());
					user.setEmail(txt_email.getText());
					user.setPhone(txt_phone.getText());
					user.setAddress(txt_address.getText());
					user.setTax_file_number(txt_tax_file_number.getText());
					user.setUser_type(userTypeComboBox.getSelectedItem().toString());
					
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					String date_of_birth = df.format(dateChooser.getDate());
					user.setDate_of_birth(date_of_birth);	
					
					/* initialize UserSErvice */
					UserService ls = new UserServiceimpl();
					if (ls.updateUser(user)) {				// use updateUser method of UserService 
						JOptionPane.showMessageDialog(null, "Updated Successfully!!!");
						
						/* Clear all the field after update */
						clearProductField();
						/* populate the data to table */
						populateUserData();

					}else {
						JOptionPane.showMessageDialog(null, "Something went wrong");
					}

				}
			}
		});
		btn_update.setForeground(Color.WHITE);
		btn_update.setBackground(new Color(255, 124, 64));
		btn_update.setBounds(10, 62, 118, 36);
		panel_1.add(btn_update);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255,200));
		panel_2.setBounds(21, 62, 860, 561);
		add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(68, 130, 236));
		panel_3.setBounds(10, 35, 149, 2);
		panel_2.add(panel_3);
		
		JLabel lblEmployees = new JLabel("Employees");
		lblEmployees.setForeground(new Color(67, 70, 86));
		lblEmployees.setFont(new Font("Calibri", Font.BOLD, 21));
		lblEmployees.setBounds(10, 0, 130, 47);
		panel_2.add(lblEmployees);
		
		JLabel label_33 = new JLabel("");
		label_33.setIcon(new ImageIcon(Drinks.class.getResource("/image/background2.jpg")));
		label_33.setBounds(0, 0, 1356, 704);
		add(label_33);
		
		panel.add(txt_email);
		// populate the data to Table
		populateUserData();

	}
	
	protected void clearProductField() {
		txt_username.setText("");				// set txt_username to "" ie empty
		txt_firstname.setText("");
		txt_lastname.setText("");
		txt_email.setText("");
		txt_phone.setText("");
		txt_tax_file_number.setText("");
		txt_address.setText("");
		dateChooser.setDate(new Date());
		
		
	}

	protected boolean userValidation() {
		/* Test all the field is empty or not if empty then error message*/
		if(txt_username.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Username");	// message username is missing
			return false;
		}
		if(txt_firstname.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Firstname");
			return false;
		}
		if(txt_lastname.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Lastname");
			return false;
		}
		if(txt_email.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please write Email Address");
			return false;
		}if(this.dateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Please choose Date");
			return false;
		} 	
		/* the all validation is correct then return true */
		return true;
	}

	private void populateUserData() {
		
		/* Get the service initialize */
		UserService ss = new UserServiceimpl();
		
		/* Use the service getUser that return us the list of user  */
		List<UserModel> sList = ss.getUser();		// get list of user to sList

		/*  initiazlize the table */ 
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		/* Loop the sList and place it in the table */
		for (UserModel s : sList) {
			model.addRow(new Object[] { s.getId(), s.getUsername(), s.getFirstname(), s.getLastname(), s.getEmail(),
					s.getDate_of_birth(), s.getPhone(),s.getTax_file_number(),s.getAddress(),s.getUser_type() });
		}
		
		
		// right click on the table then the Edit and Delete option will be appear
		popupMenu = new JPopupMenu();
		menuItemEdit = new JMenuItem("Edit        ");
		menuItemRemove = new JMenuItem("Delete     ");

		/* add the listener */
		menuItemEdit.addActionListener(this);
		menuItemRemove.addActionListener(this);

		popupMenu.add(menuItemEdit);
		popupMenu.add(menuItemRemove);

		// sets the popup menu for the table
		table.setComponentPopupMenu(popupMenu);

		/* use TableMouseListener to show pop menu */
		table.addMouseListener(new TableMouseListener(table));

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JMenuItem menu = (JMenuItem) event.getSource();
		
		
		if (menu == menuItemEdit) {						//when Edit is click from right click pop menu
			editrow();
		} else if (menu == menuItemRemove) {			// when delete is click from right click pop menu
			removeCurrentRow();
		} 
		
	}

	private void editrow() {
		int row = table.getSelectedRow();
		int id = (int) table.getModel().getValueAt(row, 0); // get the id of that row
		
		/* display on the below field */
		displayProductData(id);
		
	}

	private void displayProductData(int id) {
		
		/* Set all the value to field */
		
		this.user_id = id;		
		UserService ss = new UserServiceimpl();
		
		UserModel s = ss.getById(id);				// get the user detail using the GetById() method or service of UserSErvice
		txt_username.setText(s.getUsername());		// set the user detail from the ss  (Usermodel)
		txt_firstname.setText(s.getFirstname());
		txt_lastname.setText(s.getLastname());
		txt_email.setText(s.getEmail());
		txt_phone.setText(s.getPhone());
		txt_tax_file_number.setText(s.getTax_file_number());
		txt_address.setText(s.getAddress());
		userTypeComboBox.setSelectedItem(s.getUser_type());
		
		
		String date = s.getDate_of_birth();
		   java.util.Date date2 = null;
		try {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				// After parse set date
		   dateChooser.setDate(date2);		
	}
	
	private void removeCurrentRow() {
		if (table.getSelectedRow() < 0) {		// if the user click on the row of the table is outside the row

			JOptionPane.showMessageDialog(null, "Please select any one row");

		} else {
				// get the row where the user right click
			int row = table.getSelectedRow();
			int id = (int) table.getModel().getValueAt(row, 0);  // get the id from the table where the user right clicked
			UserService ls = new UserServiceimpl();
			
			/* Make sure the user want to delete Message Dialoage and confirm*/
			int input = JOptionPane.showConfirmDialog(null, "Are you sure? This will delete all product of this category");
			// 0=yes, 1=no, 2=cancel
			if (input == 0) {
				if (ls.deleteUser(id)) {				// delete the user by using the service DeleteUser() method it return true if deleted
						
					int selectedRow = table.getSelectedRow();
					tableModel.removeRow(selectedRow);		//remove it from the frontend table also
					/* populate all the user on the table*/
					populateUserData();
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong");
				}
			}

		}

	}
}
