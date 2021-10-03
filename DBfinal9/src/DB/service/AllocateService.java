package DB.service;

import java.util.List;

import DB.DAO.AllocateDAO;
import DB.bean.Allocate;

public class AllocateService {
	private AllocateDAO allocateDAO = new AllocateDAO();
	
	public void updateStatus(Allocate allocate){
		allocateDAO.updateStatus(allocate);
	}
	
	public void updateStatusW(String ssn, int aid, boolean accept, boolean cancel, boolean showup){
		allocateDAO.updateStatusW(ssn, aid, accept, cancel, showup);
	}
	
	public List<Allocate> getBySsn(String ssn){
		return allocateDAO.getBySsn(ssn);
	}
	
	public List<Allocate> getByPid(String pid){
		return allocateDAO.getByPid(pid);
	}
}
