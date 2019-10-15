package com.test.services;

import java.util.HashMap;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.DefaultCategoryDataset;

import com.test.models.CategoryModel;
import com.test.models.ItemCountModel;
import com.test.models.ItemEarnModel;



public interface ReportService {
	/*
	 * Calculate Today's Graph
	 */
		public DefaultCategoryDataset getGraphItemSoldToday();
		public List<ItemCountModel> getTableItemSoldToday();
		public DefaultCategoryDataset getGraphItemEarnToday();
		public List<ItemEarnModel> getTableItemEarnToday();

	/*
	 * Calculate Yesterday's for Graph and table
	 */	
		public DefaultCategoryDataset getGraphItemSoldYesterday();
		public List<ItemCountModel> getTableItemSoldYesterday();
		public DefaultCategoryDataset getGraphItemEarnYesterday();
		public List<ItemEarnModel> getTableItemEarnYesterday();

	/*
	 * Calculate Custom date's Graph and table
	 */
		public DefaultCategoryDataset getCustomSoldGraph(String start,String end);
		public List<ItemCountModel> getCustomSoldTable(String start,String end);
		public DefaultCategoryDataset getCustomEarnGraph(String start,String end);
		public List<ItemEarnModel> getCustomEarnTable(String start,String end);
	
}
