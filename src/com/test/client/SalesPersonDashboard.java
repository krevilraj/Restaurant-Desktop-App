package com.test.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.test.client.panels.CambiaPanel;
import com.test.client.panels.Drinks;
import com.test.client.panels.Employee;
import com.test.client.panels.EmployeeOrders;
import com.test.client.panels.EmployeeProcessOrders;
import com.test.client.panels.Home;
import com.test.client.panels.Menu;
import com.test.client.panels.Orders;
import com.test.client.panels.ProcessOrders;
import com.test.client.panels.Reports;
import com.test.client.panels.SalesPersonMenu;
import com.test.client.panels.Settings;
import com.test.client.panels.Stock;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Point;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class SalesPersonDashboard extends JFrame {

	private JPanel contentPane;
	private static JPanel main_panel;
	private String user_type;
	private static JPanel menu_panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesPersonDashboard frame = new SalesPersonDashboard();
					Container con = frame.getContentPane();
					con.setBackground(new Color(255, 255, 255));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SalesPersonDashboard() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		getContentPane().setBackground(new Color(246, 233, 215));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1382, 847);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(19, 122, 222));
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(SalesPersonDashboard.class.getResource("/image/dashboard_logo.png")));
		lblNewLabel.setBounds(10, 32, 203, 130);
		panel.add(lblNewLabel);

		menu_panel = new JPanel();
		menu_panel.setBackground(new Color(19, 122, 222));
		menu_panel.setBounds(0, 173, 223, 197);
		panel.add(menu_panel);
		menu_panel.setLayout(null);
		
		pnl_home = new JPanel();
		pnl_home.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetColor();
				pnl_home.setBackground(new Color(68, 130, 236));
				new CambiaPanel(main_panel, new Home());
			}
		});
		pnl_home.setBackground(new Color(19, 122, 222));
		pnl_home.setBounds(0, 0, 223, 40);
		menu_panel.add(pnl_home);
		pnl_home.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblHome.setForeground(Color.WHITE);
		lblHome.setBounds(82, 0, 131, 40);
		pnl_home.add(lblHome);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(SalesPersonDashboard.class.getResource("/image/home.png")));
		label.setBounds(40, 6, 42, 29);
		pnl_home.add(label);
		
		pnl_tkorder = new JPanel();
		pnl_tkorder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetColor();
				pnl_tkorder.setBackground(new Color(68, 130, 236));
				new CambiaPanel(main_panel, new EmployeeOrders());
			}
		});
		pnl_tkorder.setLayout(null);
		pnl_tkorder.setBackground(new Color(19, 122, 222));
		pnl_tkorder.setBounds(0, 40, 223, 40);
		menu_panel.add(pnl_tkorder);
		
		lblTakeOrder = new JLabel("Take Orders");
		lblTakeOrder.setForeground(Color.WHITE);
		lblTakeOrder.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTakeOrder.setBounds(82, 0, 131, 40);
		pnl_tkorder.add(lblTakeOrder);
		
		label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(SalesPersonDashboard.class.getResource("/image/order.png")));
		label_6.setBounds(40, 6, 42, 29);
		pnl_tkorder.add(label_6);
		
		pnl_porder = new JPanel();
		pnl_porder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetColor();
				pnl_porder.setBackground(new Color(68, 130, 236));
				new CambiaPanel(main_panel, new EmployeeProcessOrders());
			}
		});
		pnl_porder.setLayout(null);
		pnl_porder.setBackground(new Color(19, 122, 222));
		pnl_porder.setBounds(0, 80, 223, 40);
		menu_panel.add(pnl_porder);
		
		lblProcessOrder = new JLabel("Process Orders");
		lblProcessOrder.setForeground(Color.WHITE);
		lblProcessOrder.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblProcessOrder.setBounds(82, 0, 131, 40);
		pnl_porder.add(lblProcessOrder);
		
		label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(SalesPersonDashboard.class.getResource("/image/porder.png")));
		label_8.setBounds(40, 6, 42, 29);
		pnl_porder.add(label_8);
		
		pnl_stock = new JPanel();
		pnl_stock.setLayout(null);
		pnl_stock.setBackground(new Color(19, 122, 222));
		pnl_stock.setBounds(0, 120, 223, 40);
		pnl_stock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetColor();
				pnl_stock.setBackground(new Color(68, 130, 236));
				new CambiaPanel(main_panel, new Stock());
			}
		});
		menu_panel.add(pnl_stock);
		
		lblStock = new JLabel("Stock");
		lblStock.setForeground(Color.WHITE);
		lblStock.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblStock.setBounds(82, 0, 131, 40);
		pnl_stock.add(lblStock);
		
		label_12 = new JLabel("");
		label_12.setIcon(new ImageIcon(SalesPersonDashboard.class.getResource("/image/stock.png")));
		label_12.setBounds(40, 6, 42, 29);
		pnl_stock.add(label_12);
		
		pnl_logout = new JPanel();
		pnl_logout.setLayout(null);
		pnl_logout.setBackground(new Color(19, 122, 222));
		pnl_logout.setBounds(0, 160, 223, 40);
		pnl_logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		menu_panel.add(pnl_logout);
		
		lblLogout = new JLabel("Logout");
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblLogout.setBounds(82, 0, 131, 40);
		pnl_logout.add(lblLogout);
		
		label_18 = new JLabel("");
		label_18.setIcon(new ImageIcon(SalesPersonDashboard.class.getResource("/image/btn_logout.png")));
		label_18.setBounds(40, 6, 42, 29);
		pnl_logout.add(label_18);

		main_panel = new JPanel();
		main_panel.setBackground(new Color(246, 233, 215));
		main_panel.setLayout(new javax.swing.BoxLayout(main_panel, javax.swing.BoxLayout.LINE_AXIS));

		new CambiaPanel(main_panel, new Home());
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(main_panel, GroupLayout.DEFAULT_SIZE, 1127, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(main_panel, GroupLayout.PREFERRED_SIZE, 709, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(69, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
		pnl_home.setBackground(new Color(68, 130, 236));

	}

	protected void resetColor() {
		pnl_home.setBackground(new Color(19, 122, 222));
		pnl_tkorder.setBackground(new Color(19, 122, 222));
		pnl_porder.setBackground(new Color(19, 122, 222));
		pnl_stock.setBackground(new Color(19, 122, 222));
		pnl_logout.setBackground(new Color(19, 122, 222));

	}

	public static void changeFrame(JPanel content) {
		new CambiaPanel(main_panel, content);
//		setVisible(true);

	}

	
	

	public static String help = "help";
	private JPanel pnl_home;
	private JPanel pnl_tkorder;
	private JLabel lblTakeOrder;
	private JLabel label_6;
	private JPanel pnl_porder;
	private JLabel lblProcessOrder;
	private JLabel label_8;
	private JPanel pnl_stock;
	private JLabel lblStock;
	private JLabel label_12;
	private JPanel pnl_logout;
	private JLabel lblLogout;
	private JLabel label_18;
}
