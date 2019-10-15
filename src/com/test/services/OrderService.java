package com.test.services;

import java.util.HashMap;
import java.util.List;


import com.test.models.OrderModel;



public interface OrderService {

		public int addOrder(OrderModel s);
		public List<OrderModel> getOrder();
		public boolean deleteOrder(int id);
		public boolean updateOrder(OrderModel s);
		public OrderModel getById(int id);
			
	
}
