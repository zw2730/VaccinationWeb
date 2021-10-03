package DB.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import DB.bean.Patient;
import DB.bean.PatientAccount;
import DB.util.JdbcUtils;

public class PatientAccountDAO {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public void add(PatientAccount patientAccount) {
		String sql = "insert into patient_account values(?,?)";
		try {
			qr.update(sql, patientAccount.getEmail(), patientAccount.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public PatientAccount login(String email, String password){
		String sql = "select * from patient_account where email=? and password=?";
		try {
			return qr.query(sql, new BeanHandler<PatientAccount>(PatientAccount.class), email, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public PatientAccount getByEmail(String email) {
		String sql = "select * from patient_account where email=?";
		try {
			return qr.query(sql, new BeanHandler<PatientAccount>(PatientAccount.class), email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Map<String, Object>> getAll() {
		String sql = "select * from patient_account";
		try {
			return (List<Map<String, Object>>)qr.query(sql, new MapListHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	

	public static void main(String[] args){
		PatientAccountDAO pad = new PatientAccountDAO();
		System.out.print(pad.getAll().toString());
		System.out.print(pad.getByEmail("li@mail.com").toString());
		
	}
}
