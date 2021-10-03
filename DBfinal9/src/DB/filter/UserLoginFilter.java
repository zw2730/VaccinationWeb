package DB.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import DB.bean.Patient;


/**
 * Servlet Filter implementation class UserLoginFilter
 */
@WebFilter(
		urlPatterns = { 
				"/jsps/user/addPreferredTime.jsp",
				"/jsps/user/deletePreferredTime.jsp",
				"/jsps/user/editAllocateStatus.jsp",
				"/jsps/user/editBasic.jsp",
				"/jsps/user/personal.jsp"
		})
public class UserLoginFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Patient patient = (Patient)httpRequest.getSession().getAttribute("session_patient");
		if(patient != null) {
			chain.doFilter(request, response);
		} else {
			httpRequest.setAttribute("msg", "You are not login");
			httpRequest.getRequestDispatcher("/jsps/user/login.jsp")
					.forward(httpRequest, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
