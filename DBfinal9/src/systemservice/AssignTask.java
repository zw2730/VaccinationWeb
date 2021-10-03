package systemservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import DB.util.JdbcUtils;

public class AssignTask extends TimerTask {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	private int[][] M;
	private int[] match;
	private boolean[] used;
	private int n;
	private int m;
	//private static boolean isRunning = false;
	public void resetMatch() {
		int l = match.length;
		for(int i=0;i<l;i++) {
			match[i] = -1;
		}
	}
	
	public int findInd(List<Object[]> list, int x) {
		int l = list.size();
		for(int i=0;i<l;i++) {
			if(x==(Integer)list.get(i)[0]) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean find(int k) {
		for(int i=0;i<m;i++)
			{
				if(M[k][i]==1 && !used[i])
				{
					used[i]=true;
					if(match[i]==-1 || find(match[i]))
					{
						match[i]=k;
						return true;
					}
				}
			}
			return false;
	}
	
	@Override
	public void run() {
//		if(!isRunning) {
//			isRunning = true;
//			System.out.println("执行了");
//
//			isRunning = false;
//		} else {
//			System.out.println("执行错误");
//		}
		System.out.println("DB 8");
		try {
			String sql_patients="select ssn from patient as p " + 
					"where not exists(select * from allocate where allocate.ssn=p.ssn ) and p.gid= ? ";
			String sql_app="select aid from appointment as ap " + 
					"where not exists(select * from allocate where allocate.aid=ap.aid )";
			String sql_view1 = "Create or replace view no_ass_app as " + 
					"(select aid,pid,datetime " + 
					"from appointment " + 
					"where aid not in (select aid from allocate )); " ;
			
			String sql_view2 = "Create or replace view patient_schedule as " + 
					"(select day,slotStarttime,slotEndtime " + 
					"from preferred_time " + 
					"where preferred_time.ssn= ? ); ";
			
			String sql_view3 = "Create or replace view dist as " + 
					"(select pid,st_distance_sphere(point(patient.longitude,patient.latitude) " + 
					",point(provider.longitude,provider.latitude)) as d " + 
					"from patient,provider where ssn= ? ); ";
			String sql_findApp = 
					"select aid from no_ass_app natural join dist " + 
					"where weekday(date(datetime)) in (select day from patient_schedule) " + 
					"and exists (select * from patient_schedule " + 
					"			where time(datetime)>=slotStarttime and time(datetime)<=slotEndtime) " + 
					"and d < (select maxDist from patient where ssn= ? )*1000 "
					+ "and date(datetime)>=(select qualify_date from `vaccination`.`group` natural join patient " + 
					"where patient.ssn= ? )";
			
			String sql_Insert = "Insert Into allocate(ssn,aid,expireTime) values(?,?,?)";
			
			//List<Object[]> pat =  qr.query(sql_patients, new ArrayListHandler());
			
			for(int g=1;g<=5;g++) {
				List<Object[]> pat =  qr.query(sql_patients, new ArrayListHandler(),g);
				List<Object[]> app = qr.query(sql_app, new ArrayListHandler());
				
				n = pat.size();
				m = app.size();
				M = new int[n][m];
				match = new int[m];
				used = new boolean[m];
				for(int i=0;i<n;i++) {
					String ssn = (String)pat.get(i)[0];
					qr.update(sql_view1);
					qr.update(sql_view2,ssn);
					qr.update(sql_view3,ssn);
					List<Object[]> tgtApp = qr.query(sql_findApp, new ArrayListHandler(), ssn,ssn);
					int lt = tgtApp.size();
					for(int j=0;j<lt;j++) {
						int Ind = findInd(app,(Integer)tgtApp.get(j)[0]);
						M[i][Ind] = 1;
					}
				}
				resetMatch();
				for(int i=0;i<n;i++)
				{
					used = new boolean[m];
					find(i);
				}
				for(int i=0;i<m;i++) {
					if(match[i]!=-1) {
						int aid = (Integer)app.get(i)[0];
						String ssn = (String)pat.get(match[i])[0];
						java.sql.Date expireDate = new java.sql.Date(System.currentTimeMillis()+1000*60*60*24*2);
						qr.update(sql_Insert,ssn,aid,expireDate);
					}
				}
				for(int i=0;i<n;i++) {
					System.out.print("\n");
					for(int j=0;j<m;j++) {
						System.out.print(M[i][j]+" ");
					}
				}
				System.out.print("\n");
				for(int i=0;i<m;i++) {
					System.out.println(match[i]);
				}
				System.out.println("Group "+g+" has been allocated!");
			}
			
			//String ssn = "222222222";
			//List<Object[]> tgtApp = qr.query(sql_findApp, new ArrayListHandler(), ssn,ssn,ssn,ssn);
			
//			for(int i=0;i<tgtApp.size();i++) {
//				System.out.println(tgtApp.get(i)[0]);
//			}
			//System.out.println(pat);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
