package com.test.services;

import java.util.HashMap;
import java.util.List;

import com.test.models.CategoryModel;
import com.test.models.SettingModel;



public interface SettingService {

	/* Update the setting */
	public boolean updateSetting(SettingModel s);

	/* Get the Setting */
	public SettingModel getSetting();				
	
}
