package DB.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.service.AllocateService;
import DB.service.AppointmentService;
import DB.service.ProviderException;
import DB.service.ProviderService;
import DB.util.CommonUtils;
import DB.bean.Allocate;
import DB.bean.Appointment;
import DB.bean.Patient;
import DB.bean.PatientAccount;
import DB.bean.PatientPreferredTime;
import DB.bean.Provider;


public class ProviderServlet extends BaseBackServlet{
	private ProviderService providerService = new ProviderService();
	private AllocateService allocateService = new AllocateService();
	private AppointmentService appointmentService = new AppointmentService();
	
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String providerId = request.getParameter("providerId");
		Provider provider = providerService.getById(providerId);
		
		
		try{
			providerService.login(providerId);
			
			List<Map<String, Object>> appointments = appointmentService.getByPid(Integer.parseInt(providerId));
			request.getSession().setAttribute("session_provider_appointments", appointments);
			
			List<Allocate> allocateList = allocateService.getByPid(providerId);
			request.getSession().setAttribute("session_provider_allocates", allocateList);
			
			request.getSession().setAttribute("session_provider_id", providerId);
			request.getSession().setAttribute("session_provider", provider);

			request.setAttribute("msg", "successful login");
			return "r:/indexPr.jsp";
		}catch(ProviderException e){
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("providerId", providerId);
			return "f:/jsps/provider/login.jsp";
		}
	}
	
	/**
	 * Clear session and exit
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("session_provider_id");
		request.getSession().removeAttribute("session_provider");
		request.getSession().removeAttribute("session_provider_appointments");
		request.getSession().removeAttribute("session_provider_allocates");
		return "r:/indexPr.jsp";
	}

	/**
	 * 
	 * @author zilong wang
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String uploadAppointment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Appointment form = CommonUtils.toBean(request.getParameterMap(), Appointment.class);
		Map<String,String> errors = new HashMap<String,String>();
		
		// get pid from session.
		int pid = Integer.parseInt((String) request.getSession().getAttribute("session_provider_id"));
		form.setPid(pid);
		
		String datetime = form.getDatetime();
		if(datetime == null || datetime.trim().isEmpty()) {
			errors.put("datetime", "datetime can not be empty");
		}
		// @ZhiXi:regular expression
//		else if(){
//			
//		}
		
		if(errors.size() > 0) {
			// 1. save error message in request
			// 2. save form information in request for showing back 
			// 3. forward to editPreferedTime.jsp to edit again
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/patient/addAppointment.jsp";
		}
		
		appointmentService.add(form);
		
		List<Map<String, Object>> appointments = appointmentService.getByPid(form.getPid());
		request.getSession().setAttribute("session_provider_appointments", appointments);
		return "r:/jsps/provider/personal.jsp";	
	}
	
}
