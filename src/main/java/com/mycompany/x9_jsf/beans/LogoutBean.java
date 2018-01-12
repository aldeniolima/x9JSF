package com.mycompany.x9_jsf.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author joselima
 */
@ManagedBean
@ViewScoped
public class LogoutBean implements Serializable {

    public String logout() throws ServletException {
        FacesContext face = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) face.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        HttpServletRequest request = (HttpServletRequest) face.getExternalContext().getRequest();        
        request.logout();
        return "sair";
    }
}
