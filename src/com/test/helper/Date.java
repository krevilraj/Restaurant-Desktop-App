package com.test.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {
	public static String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime date = LocalDateTime.now();  
	    return date.toString();
	}
	
	public static boolean compareDates(String d1,String d2)
    {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date1 = sdf.parse(d1);
            java.util.Date date2 = sdf.parse(d2);

            if(date1.before(date2)){
               return true;
            }         
            
           return false;
        }
        catch(ParseException ex){
            ex.printStackTrace();
            return false;
        }
    }

}
