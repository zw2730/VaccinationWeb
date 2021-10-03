package DB.DAO;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import DB.bean.Patient;
import DB.util.DateConverter;
import DB.util.JdbcUtils;



public class PatientDAO {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	private DateConverter dateConverter = new DateConverter();
	public void add(Patient patient){
		try{
			String sql = "insert into patient values(?,?,?,?,?,?,?,?,?,?)";
			qr.update(sql, patient.getSsn(), patient.getName(), 
					patient.getBirthdate(),
					patient.getAddress(), patient.getPhone(),patient.getMaxDist(),
					patient.getLatitude(), 
					patient.getLongitude(),
					patient.getEmail(),
					patient.getGid()
					);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public Patient getBySsn(String ssn){
		String sql = "select * from patient where ssn=?";
		try {
			return qr.query(sql, new BeanHandler<Patient>(Patient.class), ssn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Patient getByEmail(String email) {
		String sql = "select * from patient where email=?";
		try {
			return qr.query(sql, new BeanHandler<Patient>(Patient.class), email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public void update(Patient patient) {
		String sql = "update patient set address = ?, maxDist = ?,"
				+ "phone = ?, latitude = ?, longitude = ? , "
				+ "birthdate=?, gid=? where ssn = ?";
		
		try {
			qr.update(sql, patient.getAddress(), patient.getMaxDist(),
					patient.getPhone(), patient.getLatitude(), patient.getLongitude(),
					patient.getBirthdate(),
					patient.getGid(),patient.getSsn());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
