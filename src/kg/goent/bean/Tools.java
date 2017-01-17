package kg.goent.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by Aziret on 16.01.2017.
 */
@ManagedBean
@ViewScoped
public class Tools implements Serializable {
	public static void killInfoSession(){
		SessionTools.setSession("infoMessageSession", null);
	}
}
