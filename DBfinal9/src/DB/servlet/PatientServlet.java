package DB.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.bean.Allocate;
import DB.bean.Patient;
import DB.bean.PatientAccount;
import DB.bean.PatientPreferredTime;
import DB.service.AllocateService;
import DB.service.PatientException;
import DB.service.PatientPreferredTimeService;
import DB.service.PatientService;
import DB.util.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

/**
 * 
 * @author zilong wang
 *
 */
public class PatientServlet extends BaseBackServlet {
	
	private PatientService patientService = new PatientService();
	private PatientPreferredTimeService patientPreferredTimeService = new PatientPreferredTimeService();
	private AllocateService allocateService = new AllocateService();
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
		request.getSession().removeAttribute("session_patient");
		request.getSession().removeAttribute("session_patient_allcotes");
		request.getSession().removeAttribute("session_patient_account");
		request.getSession().removeAttribute("session_patient_preferredTime");
		return "r:/index.jsp";
	}
	
	public String updateAllocateStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, PatientException {
		Allocate form = CommonUtils.toBean(request.getParameterMap(), Allocate.class);
		Map<String,String> errors = new HashMap<String,String>();
		
		String ssn = ((Patient) request.getSession().getAttribute("session_patient")).getSsn();
		form.setSsn(ssn);
		
		boolean accept = form.isAccept();
		boolean showUp = form.isShowUp();
		boolean cancel = form.isCancel();
		
		if(accept == false && cancel == true){
			errors.put("isCancel", "you can not cancel unaccept allocate");
		}
		
		if(accept == false && showUp == true){
			errors.put("isShowUp", "you can not access unaccept allocate");
		}
		
		if(showUp == true && cancel == true){
			errors.put("isShowUp", "you can not access canceled allocate");
		}
		
		if(errors.size() > 0) {
			// 1. save error
			// 2. save form
			// 3. redirect to editBasic.jsp
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/editAllocateStatus.jsp";
		}
		
		System.out.println("this is update allocate");
		System.out.println(form);
		
		allocateService.updateStatus(form);
		List<Allocate> alllocateList = allocateService.getBySsn(ssn);
		request.getSession().setAttribute("session_patient_allcotes", alllocateList);

		return "r:/jsps/user/personal.jsp"; 
	}
	
	public String update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, PatientException {
		Patient form = CommonUtils.toBean(request.getParameterMap(), Patient.class);
		Map<String,String> errors = new HashMap<String,String>();
		
		
//      @Zhixi: Please add regular expression 
		String birthdate = form.getBirthdate();
		if(birthdate == null || birthdate.trim().isEmpty()) {
			errors.put("birthdate", "birthdate can not be empty");
		} 
//		else if(!birthdate.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
//			errors.put("birthdate", "birthdate format wrong");
//		}
		
		String address = form.getAddress();
		if(address == null || address.trim().isEmpty()) {
			errors.put("address", "address can not be empty");
		}
		
		String phone = form.getPhone();
		if(phone == null || phone.trim().isEmpty()) {
			errors.put("phone", "phonecan not be empty");
		} else if(!phone.matches("\\w+") || phone.length() != 10 ) {
			errors.put("phone", "phone format wrong");
		}
		
		if(errors.size() > 0) {
			// 1. save error
			// 2. save form
			// 3. redirect to editBasic.jsp
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/editBasic.jsp";
		}
		
		form.setGid(patientService.getGroup(form));
		
		List<Double> langWithLong = patientService.getLangtitudeWithLongitude(form.getAddress());
		form.setLatitude(langWithLong.get(0));
		form.setLongitude(langWithLong.get(1));
		String ssn = ((Patient) request.getSession().getAttribute("session_patient")).getSsn();
		form.setSsn(ssn);
		patientService.update(form);
		
		// We do not update all information of patient, so need to 
		//get whole information and store in session
		Patient p = patientService.getBySsn(ssn);
		request.getSession().setAttribute("session_patient", p);
		return "r:/jsps/user/personal.jsp";
	}
	
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, PatientException {
		Patient form = CommonUtils.toBean(request.getParameterMap(), Patient.class);

		Map<String,String> errors = new HashMap<String,String>();
		String ssn = form.getSsn();
		if(ssn == null || ssn.trim().isEmpty()) {
			errors.put("ssn", "ssn cannot be empty");
		} else if(ssn.length() != 9) {
			errors.put("ssn", "length must be 9");
		} else if(ssn.equals("null") || ssn.equals("Null")){
			errors.put("ssn", "illegal ssn");
		}
		
		String name = form.getName();
		if(name == null || name.trim().isEmpty()) {
			errors.put("name", "name cannot be empty");
		} else if(name.length() < 3 || name.length() > 20) {
			errors.put("name", "length must between 3-20");
		} else if(name.equals("null") || name.equals("Null")){
			errors.put("name", "illegal name");
		}
		
		String email = form.getEmail();
		if(email == null || email.trim().isEmpty()) {
			errors.put("email", "Email can not be empty");
		} else if(!email.matches("\\w+@\\w+\\.\\w+")) {
			errors.put("email", "Email format wrong");
		}

//      @Zhixi: Please add regular expression 
		String birthdate = form.getBirthdate();
		if(birthdate == null || birthdate.trim().isEmpty()) {
			errors.put("birthdate", "birthdate can not be empty");
		} 
//		else if(!birthdate.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
//			errors.put("birthdate", "birthdate format wrong");
//		}
		
		String address = form.getAddress();
		if(address == null || address.trim().isEmpty()) {
			errors.put("address", "address can not be empty");
		}
		
		String phone = form.getPhone();
		if(phone == null || phone.trim().isEmpty()) {
			errors.put("phone", "phonecan not be empty");
		} else if(!phone.matches("\\w+") || phone.length() != 10 ) {
			errors.put("phone", "phone format wrong");
		}
		
		if(errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		form.setGid(patientService.getGroup(form));
		
		/*
		 * get Langtitude and Longitude from loaction
		 */
		List<Double> langWithLong = patientService.getLangtitudeWithLongitude(form.getAddress());
		form.setLatitude(langWithLong.get(0));
		form.setLongitude(langWithLong.get(1));
		/*
		 * call service regist()
		 */
		
		/*
		 * send mail
		 */
		// get properties
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader()
				.getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host");//获取服务器主机
		String uname = props.getProperty("uname");//获取用户名
		String pwd = props.getProperty("pwd");//获取密码
		String from = props.getProperty("from");//获取发件人
		String to = email;//获取收件人
		String subject = props.getProperty("subject");//获取主题
		String content = email;//获取邮件内容
		
		Session session = MailUtils.createSession(host, uname, pwd);
		Mail mail = new Mail(from, to, subject, content);
		try {
			System.out.print("ready to send mail");
			MailUtils.send(session, mail);
			System.out.println("sended");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		
		
		try {
			patientService.registPatient(form);
			PatientAccount pat = new PatientAccount();
			pat.setEmail(email);
			//pat.setPassword(email);
			pat.setPassword("1234567890");
			patientService.registAccount(pat);
		} catch (PatientException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		request.setAttribute("msg", "register success");
		return "f:/jsps/msg.jsp";
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		PatientAccount form = CommonUtils.toBean(request.getParameterMap(), PatientAccount.class);

		try{
			PatientAccount pa = patientService.login(email, password);
			Patient p = patientService.getByEmail(email);
			List<Allocate> alllocateList = allocateService.getBySsn(p.getSsn());
			List<Map<String, Object>> pft = patientPreferredTimeService.getBySsn(p.getSsn());
			
			request.getSession().setAttribute("session_patient_account", pa);
			request.getSession().setAttribute("session_patient", p);
			request.getSession().setAttribute("session_patient_preferredTime", pft);
			request.getSession().setAttribute("session_patient_allcotes", alllocateList);
			request.setAttribute("msg", "successful login");
			return "r:/index.jsp";
		}catch(PatientException e){
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/login.jsp";
		}
	}

	/**
	 * Upload patient's preferred time slot
	 * 
	 * @author zilong wang
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws PatientException
	 */
	public String uploadPreferredTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, PatientException {
		PatientPreferredTime form = CommonUtils.toBean(request.getParameterMap(), PatientPreferredTime.class);
		Map<String,String> errors = new HashMap<String,String>();
		
		// get ssn from session.
		String ssn = ((Patient) request.getSession().getAttribute("session_patient")).getSsn();
		form.setSsn(ssn);

		int day = form.getDay();
		if((day+"").trim().isEmpty()) {
			errors.put("preferredDay", "day can not be empty");
		} else if(day > 6 || day < 0) {
			errors.put("preferredDay", "day should be 0-6");
		}
		
//      @Zhixi: Please add right regular expression 
		String slotStarttime = form.getSlotStarttime();
		if(slotStarttime == null || slotStarttime.trim().isEmpty()) {
			errors.put("slotStarttime", "slotStarttime can not be empty");
		}
//		else if(!slotStarttime.matches("\\w+@\\w+\\.\\w+")) {
//			errors.put("slotStarttime", "slotStarttime format wrong");
//		}

//      @Zhixi: Please add right regular expression 
		String slotEndtime = form.getSlotEndtime();
		if(slotEndtime == null || slotEndtime.trim().isEmpty()) {
			errors.put("slotEndtime", "slotEndtime can not be empty");
		} 
//		else if(!slotEndtime.matches("\\w+@\\w+\\.\\w+")) {
//			errors.put("slotEndtime", "slotEndtime format wrong");
//		}
		
		if(patientPreferredTimeService.isExist(form)){
			errors.put("slotEndtime", "these day, start time, end time have been rigistered");
		}
		
		if(errors.size() > 0) {
			// 1. save error message in request
			// 2. save form information in request for showing back 
			// 3. forward to editPreferedTime.jsp to edit again
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/addPreferredTime.jsp";
		}
		
		patientPreferredTimeService.add(form);
		
		List<Map<String, Object>> pft = patientPreferredTimeService.getBySsn(form.getSsn());
		request.getSession().setAttribute("session_patient_preferredTime", pft);
		return "r:/jsps/user/personal.jsp";
		
	}
	
    /**
     * Delete patient preferred time day and slot
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws PatientException
     */
	public String deletePreferredTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, PatientException {
		PatientPreferredTime form = CommonUtils.toBean(request.getParameterMap(), PatientPreferredTime.class);
		Map<String,String> errors = new HashMap<String,String>();
		
		// get ssn from session.
		String ssn = ((Patient) request.getSession().getAttribute("session_patient")).getSsn();
		form.setSsn(ssn);
		
		if(!patientPreferredTimeService.isExist(form)){
			System.out.println("sechdule not exist");
			errors.put("deletePreferredTime", "this sechdule does not exist");
		}
		
		if(errors.size() > 0) {
			// 1. save error message in request
			// 2. save form information in request for showing back 
			// 3. forward to deletePreferedTime.jsp to delete again
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			System.out.println("sechdule not exist, forward");
			return "f:/jsps/user/deletePreferredTime.jsp";
		}
		
		patientPreferredTimeService.delete(form);
		
		List<Map<String, Object>> pft = patientPreferredTimeService.getBySsn(form.getSsn());
		request.getSession().setAttribute("session_patient_preferredTime", pft);
		return "r:/jsps/user/personal.jsp";
	}
	
	//undo
	public String getPatientAssign(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, PatientException {
		return "";
	}
	
}
