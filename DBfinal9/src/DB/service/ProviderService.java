package DB.service;

import java.sql.SQLException;

import DB.DAO.ProviderDAO;
import DB.bean.PatientAccount;
import DB.bean.Provider;

public class ProviderService {
	private ProviderDAO providerDAO = new ProviderDAO();

	//no need to reconstruct
	public Provider getById(String id) {
		return providerDAO.getById(id);
	}
	
	public void login(String providerId) throws ProviderException {
		Provider pro= providerDAO.getById(providerId);
		if(pro == null) throw new ProviderException("No such provider");;		
	}
	
	
	
}
