package com.test.client.jframes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.test.models.OrderModel;
import com.test.models.ProductOrderModel;
import com.test.serviceimpl.OrderServiceimpl;
import com.test.serviceimpl.ProductOrderServiceimpl;
import com.test.services.OrderService;
import com.test.services.ProductOrderService;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Printer extends JFrame {

	private JPanel contentPane;
	private JTextArea area;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Printer frame = new Printer();
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
	public Printer() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		area = new JTextArea();
		area.setBounds(10, 11, 414, 435);
		contentPane.add(area);
		
		button = new JButton("Print");
		button.setFont(new Font("Segoe UI", Font.BOLD, 16));
		button.setIconTextGap(16);
		button.setIcon(new ImageIcon(Printer.class.getResource("/image/printer.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//  call print() method to print the textarea
					area.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setBorder(null);
		button.setBackground(new Color(0, 102, 204));
		button.setBounds(298, 475, 126, 36);
		contentPane.add(button);
		setVisible(true);   // constructor set the visibility
	}
	
	public void displaybyId(int order_id,String sub_total,String discount,String gst,String grand_total) {
		/* Initialize the service */
		ProductOrderService ls = new ProductOrderServiceimpl();
		
		/* Get the order list from the SErvice using getProductOrder() method */
		List<ProductOrderModel> sList = ls.getProductOrder(order_id);
		
		
		/* Making the Design of the bill */
		
		String receipt ="*******************************************************\n"; 
		receipt += "*                   Afghan Kebab                          *\n"; 
		receipt += "*******************************************************\n";
		
		
		String date = com.test.helper.Date.getDate();
		
		receipt += "\n Date: "+date;
		receipt += "\n Order Number : "+order_id+"\n";
		receipt += "S.N Qty Item Name \t   Price\n";
		
		int i =1;
		
		/* Loop the list till we get all the product of the order_id */
		for (ProductOrderModel s : sList) {
			receipt += i+"    "+s.getQuantity()+" "+s.getProduct_name()+"\t\t : \t  "+s.getPrice()+" \n";
			i++;
		}
		receipt += "\nSubtotal :"+sub_total;			// sub_total value can be get from the argument above which call by Process Order class
		receipt += "\nDiscount :"+discount;				// discount value from parameter
		receipt += "\nGst      :"+gst;					// gst value from the parameter
		receipt += "\nGrandTotal :"+grand_total;		// grandtotal from the parameter
		
		
		/* set all the value of receipt to textarea  then we get something like this*/
		area.setText(receipt);

		
	}
	
	
}
