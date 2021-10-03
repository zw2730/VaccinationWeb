package DB.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import DB.DAO.PatientAccountDAO;
import DB.DAO.PatientDAO;
import DB.DAO.PatientPreferredTimeDAO;
import DB.bean.Patient;
import DB.bean.PatientAccount;
import DB.bean.PatientPreferredTime;

public class PatientService {
	private PatientDAO patientDAO = new PatientDAO();
	private PatientAccountDAO patientAccountDAO = new PatientAccountDAO();
	
	public void registPatient(Patient form) throws PatientException{
		Patient patient = patientDAO.getBySsn(form.getSsn());
		if(patient != null)throw new PatientException("This ssn has been registered");
		
		patient = patientDAO.getByEmail(form.getEmail());
		if(patient != null) throw new PatientException("This email has been registered");
		patientDAO.add(form);
	}
	
	public int getGroup(Patient patient){
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String nowYear = df.format(new Date());
		int age = Integer.parseInt(nowYear) - Integer.parseInt(patient.getBirthdate().substring(0, 4));
		if(age>=75) {
			return 5;
		}
		else if(age>=65) {
			return 1;
		}
		else if(age>=50) {
			return 2;
		}
		else if(age>=30) {
			return 3;
		}
		else if(age>=18) {
			return 4;
		}
		return 5;
	}
	
	public List<Double> getLangtitudeWithLongitude(String location){
		String KEY_1 = "gmrlcOIhv62EG84DUYp1piDIDeLrqASt";
		BufferedReader in = null;
		Map<String,String> map = null;
        try {

            location = URLEncoder.encode(location, "UTF-8");
            URL tirc = new URL("http://api.map.baidu.com/geocoding/v3/?address=" +
                    location + "&output=json&ak=" + KEY_1);
            in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            String str = sb.toString();
            //Map map = null;
            System.out.println(str);
            if (StringUtils.isNotEmpty(str)) {
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                    String lng = str.substring(lngStart + 5, lngEnd);
                    String lat = str.substring(lngEnd + 7, latEnd);
                    map = new HashMap<String,String>();
                    map.put("lng", lng);
                    map.put("lat", lat);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		List<Double> list= new LinkedList<>();
		list.add(Double.parseDouble(map.get("lat")));
		list.add(Double.parseDouble(map.get("lng")));
		return list;
	}
	
	public void registAccount(PatientAccount patientAt){
		patientAccountDAO.add(patientAt);
	}
	
	public Patient getBySsn(String ssn){
		return patientDAO.getBySsn(ssn);
	}
	
	public Patient getByEmail(String email){
		return patientDAO.getByEmail(email);
	}
	
	public void update(Patient patient){
		patientDAO.update(patient);
	}
	
	public PatientAccount login (String email, String password) throws PatientException{
		
		PatientAccount pa = patientAccountDAO.getByEmail(email);
		if(pa == null) throw new PatientException("No such email");
		
		if(!pa.getPassword().equals(password))
			throw new PatientException("Password Wrong");
		
		return patientAccountDAO.login(email, password);
	}
}
