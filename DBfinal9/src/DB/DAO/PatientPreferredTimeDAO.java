package DB.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import DB.bean.Patient;
import DB.bean.PatientPreferredTime;
import DB.util.JdbcUtils;
import DB.util.TimeConverter;

public class PatientPreferredTimeDAO {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	private TimeConverter tc = new TimeConverter();
	
	public void add(PatientPreferredTime pft){
		try{
			String sql = "insert into preferred_time values(?,?,?,?)";
			qr.update(sql, pft.getSsn(), pft.getDay(), 
					tc.convert(java.sql.Time.class,pft.getSlotStarttime()),
					tc.convert(java.sql.Time.class,pft.getSlotEndtime()));
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Map<String,Object>> getBySsn(String ssn){
		String sql = "select * from preferred_time where ssn=?";
		try {
			return qr.query(sql, new MapListHandler(), ssn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean isExist(PatientPreferredTime pft){
		String sql = "select * from preferred_time where ssn=? and day = ? and "
				+ "slotStarttime = ? and "
				+ "slotEndtime = ?";
		try {
			PatientPreferredTime result =  qr.query(sql, 
					new BeanHandler<PatientPreferredTime>(PatientPreferredTime.class),
					pft.getSsn(), pft.getDay(), pft.getSlotStarttime(), pft.getSlotEndtime());
			
			if(result == null) return false;
			else return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(PatientPreferredTime pft) {
		String sql = "update preferred_time set day = ?, slotStarttime = ?,"
				+ "slotEndtime = ? where ssn = ?";
		System.out.println(pft);
		try {
			qr.update(sql, pft.getDay(),
					tc.convert(java.sql.Time.class,pft.getSlotStarttime()),
					tc.convert(java.sql.Time.class,pft.getSlotEndtime()),
					pft.getSsn());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public void delete(PatientPreferredTime pft) {
		String sql = "delete from preferred_time where day = ? and slotStarttime = ? and "
				+ "slotEndtime = ? and ssn = ?";
		System.out.println(pft);
		try {
			qr.update(sql, pft.getDay(),
					tc.convert(java.sql.Time.class,pft.getSlotStarttime()),
					tc.convert(java.sql.Time.class,pft.getSlotEndtime()),
					pft.getSsn());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args){
		PatientPreferredTimeDAO p = new PatientPreferredTimeDAO();
		PatientPreferredTime pft = new PatientPreferredTime();
		pft.setSsn("222222222");
		pft.setDay(3);
		pft.setSlotStarttime("08:00:00");
		pft.setSlotEndtime("11:00:00");
		System.out.println(p.isExist(pft));
	}


}
