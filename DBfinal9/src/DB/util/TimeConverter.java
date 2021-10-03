package DB.util;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class TimeConverter implements Converter {

	@Override
	public Object convert(Class type, Object value) {
		if(type != java.sql.Time.class) {
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
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			Date date = sdf.parse(s);
			System.out.println(date);
			System.out.println(date.getTime());
			return new java.sql.Time(date.getTime()); 
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

}
