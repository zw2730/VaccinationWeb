package DB.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import DB.bean.Appointment;
import DB.bean.PatientPreferredTime;
import DB.util.JdbcUtils;

public class AppointmentDAO {
	private Appointment appointment = new Appointment();
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public void add(Appointment app) {
		try{
			String sql = "insert into appointment values(null,?,?)";
			qr.update(sql, app.getDatetime(), app.getPid());
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public Appointment getByAid(int aid){
		try{
			String sql = "select * from appointment where aid=?";
			return qr.query(sql,
					new BeanHandler<Appointment>(Appointment.class), aid);
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public List<Map<String, Object>> getByPid(int pid) {
		String sql = "select * from appointment where pid=?";
		try {
			return qr.query(sql, new MapListHandler(), pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args){
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		List<Map<String, Object>> appointment = appointmentDAO.getByPid(1);
		for(Map m:appointment){
			for(Object key: m.keySet()){
				System.out.print(key);
				System.out.print(m.get(key));
			}
		}
		Appointment ap = new Appointment();
		ap.setDatetime("2021-04-05 08:00:00");
		ap.setPid(1);
		appointmentDAO.add(ap);
		System.out.println(appointment);
	}
}
