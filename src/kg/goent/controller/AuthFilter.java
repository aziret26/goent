package kg.goent.controller;

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

	@ManagedProperty(value="#{user}")
	private UserSession userSession;

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public UserSession getUserSession() {
		return userSession;
	}

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

//				if(userSession.getLogged() == 1){
//					resp.sendRedirect(reqt.getContextPath() + "/index.xhtml");
//				}
			}
			chain.doFilter(request, response);
			System.out.println("user "+userSession.getUser());

			/*else
			if (reqURI.indexOf("/login.xhtml") >= 0
				|| (ses != null && ses.getAttribute("user") != null)
				|| reqURI.indexOf("/public/") >= 0
				|| reqURI.contains("javax.faces.resource"))
				chain.doFilter(request, response);
			else
				resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}