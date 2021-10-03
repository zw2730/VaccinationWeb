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
import DB.bean.Provider;

/**
 * Servlet Filter implementation class ProviderLoginFilter
 */
@WebFilter(
		urlPatterns = { 
				"/jsps/provider/addAppointment.jsp",
				"/jsps/provider/personal.jsp"
		})
public class ProviderLoginFilter implements Filter {
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Provider provider = (Provider)httpRequest.getSession().getAttribute("session_provider");
		if(provider != null) {
			chain.doFilter(request, response);
		} else {
			httpRequest.setAttribute("msg", "You are not login");
			httpRequest.getRequestDispatcher("/jsps/provider/login.jsp")
					.forward(httpRequest, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
