package DB.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateConverter implements Converter {
	public Object convert(Class type, Object value) {
		if(type != java.util.Date.class) {
			return null;
		}
		if(value == null) {
			return null;
		}
		if(!(value instanceof String)) {
			return null;
		}
		String s = (String)value;
		if(s.length() == 0) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(s);
			System.out.println(date);
			System.out.println(date.getDate());
			System.out.println(date.getDay());
			System.out.println(date.getTime());
			System.out.println(date);
			return new java.sql.Date(date.getTime()); 
		} catch(Exception e) {
			throw new RuntimeException(e);
		}	
	}
	
	public Object convertDateTime(Class type, Object value) {
		if(type != java.util.Date.class) {
			return null;
		}
		if(value == null) {
			return null;
		}
		if(!(value instanceof String)) {
			return null;
		}
		String s = (String)value;
		if(s.length() == 0) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(s);
			System.out.println(date);
			System.out.println(date.getDate());
			System.out.println(date.getDay());
			System.out.println(date.getTime());
			System.out.println(date);
			return new Timestamp(date.getTime()); 
		} catch(Exception e) {
			throw new RuntimeException(e);
		}	
	}
	
	
	public static void main(String[] args) {
		DateConverter c = new DateConverter();
		Date date = (Date)c.convertDateTime(java.util.Date.class, "2013-04-23 08:23:23");
		System.out.println(date);
		String s =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//		System.out.println(date.getHours());
		
		date = new Date();  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	}
}
