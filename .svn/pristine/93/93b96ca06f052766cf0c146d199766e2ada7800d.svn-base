package com.naja.util;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Criteria;
import org.hibernate.Session;

public class Util {

    public static String pegarIp() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context
                .getExternalContext().getRequest();
        return request.getRemoteAddr();

    }

    public static String pegarURL() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc
                .getExternalContext().getRequest();
        return request.getRequestURI();
    }
   
    //Retorna um objeto Criteria
    public static Criteria getCriteria(EntityManager manager,Class obj) {
        return manager.unwrap(Session.class).createCriteria(obj);
    }

}