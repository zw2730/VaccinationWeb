package DB.service;

import java.util.List;
import java.util.Map;

import DB.DAO.AppointmentDAO;
import DB.bean.Appointment;

public class AppointmentService {
	private AppointmentDAO appointmentDAO = new AppointmentDAO();

	public void add(Appointment form) {
		appointmentDAO.add(form);
	}

	public List<Map<String, Object>> getByPid(int pid) {
		// TODO Auto-generated method stub
		return appointmentDAO.getByPid(pid);
	}
}
