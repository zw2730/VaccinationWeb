package DB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import DB.util.DBUtil;
import DB.util.DateConverter;
import DB.util.JdbcUtils;
import DB.bean.Patient;
import DB.bean.Provider;

public class ProviderDAO {	
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	private DateConverter dateConverter = new DateConverter();
	
	public Provider getById(String id) {
		String sql = "select * from provider where pid=?";
		try {
			return qr.query(sql, new BeanHandler<Provider>(Provider.class), Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args){
		Provider p = new Provider();
		p.setPhone("1111111111");
		p.setName("Jack");
		p.setAddress("somewhere");
		
		
		ProviderDAO pdao = new ProviderDAO();
		p = pdao.getById(1+"");
		System.out.print(p);
//		pdao.add(p);
//		System.out.println(pdao.get(1).getName().toString());
//		System.out.println(pdao.get(6).getName().toString());
//		
//		Provider p1 = new Provider();
//		p1.setPid(4);
//		p1.setPhone("1111111111");
//		p1.setName("JackMa");
//		p1.setAddress("somewhere");
//		
//		pdao.update(p1);
//		System.out.println(pdao.get(6).getName().toString());
//		
//		pdao.delete(8);
//		System.out.println(pdao.isExist("JackMa"));
	}
}
