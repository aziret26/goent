package kg.goent.controller;


import kg.goent.bean.Tools;
import kg.goent.bean.User;


import java.io.IOException;
import java.util.Map;
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

			if(reqURI.contains("/activate.xhtml")){
				Map<String,String> params = Tools.getRequestParameterMap();
				if(ses != null){
					User u = (User) ses.getAttribute("user.user");

					if(u != null && u.getActivationKey().length() == 0){
						resp.sendRedirect(reqt.getContextPath() + "/index.xhtml");
					}

					if(!params.containsKey("activate")){
						resp.sendRedirect(reqt.getContextPath() + "/index.xhtml");
					}
				}

				if(params.containsKey("activate")){

					String key = params.get("activate");
					String email = Tools.decode(key);
					RegisterUser ru = new RegisterUser();

					if(!ru.activateByLink(email)){
						Tools.faceMessageWarn(
								"Sorry, but this link is not valid.",
								"");
					}else{
						Tools.faceMessageWarn(
							"You successfully activated your account",
							"Now you will be redirected to authorization page.");
						Thread.sleep(4000);
						resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
					}
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