package DB.service;

import java.util.List;
import java.util.Map;

import DB.DAO.PatientPreferredTimeDAO;
import DB.bean.PatientPreferredTime;

public class PatientPreferredTimeService {
	private PatientPreferredTimeDAO patientPreferredTimeDAO = new PatientPreferredTimeDAO();
	
	public void add(PatientPreferredTime form) throws PatientException{
		patientPreferredTimeDAO.add(form);
	}
	
	public void update(PatientPreferredTime form) throws PatientException{
		List<Map<String, Object>> pft = patientPreferredTimeDAO.getBySsn(form.getSsn());
		if(pft==null)throw new PatientException("This ssn has not registered prefered time, cannot update");
		patientPreferredTimeDAO.update(form);
	}
	
	public List<Map<String, Object>> getBySsn(String ssn){
		return patientPreferredTimeDAO.getBySsn(ssn);
	}
	
	public boolean isExist(PatientPreferredTime pft){
		return patientPreferredTimeDAO.isExist(pft);
	}

	public void delete(PatientPreferredTime form) {
		patientPreferredTimeDAO.delete(form);
		
	}
}
