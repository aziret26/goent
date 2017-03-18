/*
package kg.goent.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
*
 * Created by root on 1/10/17.


@ManagedBean
@SessionScoped
public class SessionTools {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
			.getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
			.getExternalContext().getRequest();
	}

	public static void setSession(String str,Object obj){
		HttpSession session = SessionTools.getSession();
		session.setAttribute(str, obj);
	}

	public static void setSession(String str,int i){
		HttpSession session = SessionTools.getSession();
		session.setAttribute(str, i);
	}
	public static void setSession(String str,double i){
		HttpSession session = SessionTools.getSession();
		session.setAttribute(str, i);
	}public static void setSession(String str,boolean i){
		HttpSession session = SessionTools.getSession();
		session.setAttribute(str, i);
	}
}
*/
