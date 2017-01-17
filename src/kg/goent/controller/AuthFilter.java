package kg.goent.controller;

import kg.goent.bean.SessionTools;
import kg.goent.bean.UserSession;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by root on 1/10/17.
 */

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);
			String reqURI = reqt.getRequestURI();
			if(reqURI.contains("/login.xhtml") || reqURI.contains("/signup.xhtml")){
				if(ses != null)
					if( ses.getAttribute("userLogged") != null){
						if(ses.getAttribute("userLogged").equals("1"))
							resp.sendRedirect(reqt.getContextPath() + "/index.xhtml");
					}
			}
			chain.doFilter(request, response);

		} catch (Exception e) {
			System.out.println("Auth error: "+e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {

	}
}