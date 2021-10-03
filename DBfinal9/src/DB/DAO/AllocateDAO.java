package DB.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import DB.bean.Allocate;
import DB.bean.Appointment;
import DB.bean.Patient;
import DB.bean.Provider;
import DB.util.JdbcUtils;
import DB.util.CommonUtils;

public class AllocateDAO {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public void updateStatus(Allocate allocate){
		System.out.println("this is allocate dao");
		System.out.println(allocate);
		String sql = "update allocate set  accept = ?, showUp = ?, "
				+ "cancel = ? where ssn = ? and aid = ?";
		try {
			qr.update(sql, allocate.isAccept(),
					allocate.isShowUp(),allocate.isCancel(), 
					allocate.getSsn(), allocate.getAid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	
	public void updateStatusW(String ssn, int aid, boolean accept, boolean cancel, boolean showup){
//		System.out.println("this is allocate dao");
//		System.out.println(allocate);
		String sql = "update allocate set  accept = ?, showUp = ?, "
				+ "cancel = ? where ssn = ? and aid = ?";
		try {
			int res = qr.update(sql, accept,
					showup,cancel, 
					ssn, aid);
			System.out.println("this is sql result " + res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public List<Allocate> getBySsn(String ssn){
		/*
		 * query three tables: provider, allocate, patient,  
		 */
		String sql = "select * from allocate natural join appointment natural join provider where ssn=?";

		List<Map<String, Object>> mapList = null;
		try {
			mapList = qr.query(sql, new MapListHandler(), ssn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Allocate> orderItemList = toAllocateList(mapList);
		return orderItemList;
	}
	
	public List<Allocate> getByPid(String pid) {
		/*
		 * query three tables: provider, allocate, patient,  
		 */
		String sql = "select * from allocate natural join appointment natural join patient where pid=?";

		List<Map<String, Object>> mapList = null;
		try {
			mapList = qr.query(sql, new MapListHandler(),Integer.parseInt(pid));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Allocate> orderItemList = toAllocateList(mapList);
		return orderItemList;
	}

	private List<Allocate> toAllocateList(List<Map<String, Object>> mapList) {
		List<Allocate> allocateList = new ArrayList<Allocate>();
		for(Map<String,Object> map : mapList) {
			Allocate item = toAllocate(map);
			allocateList.add(item);
		}
		return allocateList;
	}

	private Allocate toAllocate(Map<String, Object> map) {
		Allocate allocate = CommonUtils.toBean(map, Allocate.class);
		Appointment appointment = CommonUtils.toBean(map, Appointment.class);
		Provider provider = CommonUtils.toBean(map, Provider.class);
		Patient patient = CommonUtils.toBean(map, Patient.class);
		allocate.setProvider(provider);
		allocate.setAppointment(appointment);
		allocate.setPatient(patient);
		return allocate;
	}
	

	public static void main(String[] args) throws SQLException{
		AllocateDAO allocateDAO = new AllocateDAO();
//		List<Allocate> allocateList = allocateDAO.getBySsn("222222222");
//		for(Allocate a : allocateList){
//			System.out.println(a);
//			System.out.println(a.getAppointment());
//			System.out.println(a.getProvider());
//			System.out.println(a.getPatient());
//		}
		List<Allocate> allocateList = allocateDAO.getByPid(1+"");
		for(Allocate a : allocateList){
			System.out.println(a);
			System.out.println(a.getAppointment());
			System.out.println(a.getProvider());
			System.out.println(a.getPatient());
		}
		
		Allocate allocate = new Allocate();
		allocate.setSsn("222222222");
		allocate.setAid(19);
		allocate.setAccept(true);
		allocate.setShowUp(true);
		allocate.setCancel(true);
		
		allocateDAO.updateStatus(allocate);
		
	}
	
	
	
}
