package controllers;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import beans.User;

@ManagedBean
@ViewScoped
public class FormController {

	
	
	
	public String onSubmit(User user) {
		FacesContext.getCurrentInstance().getAttributes().put("user", user);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = ec.getSessionMap();
		
		sessionMap.put("user", user);
		
		return "TestResponse.xhtml";
	}
	
	public String onFlash(User user) {
//		FacesContext.getCurrentInstance().getAttributes().put("user", user);
//		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", user);
//		Map<String, Object> sessionMap = ec.getSessionMap();
//		
//		sessionMap.put("firstname", user.getFirstName());
//		sessionMap.put("lastname", user.getLastName());
//		
		return "TestResponse2.xhtml?faces-redirect=true";
	}
}