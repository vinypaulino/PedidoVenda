package com.naja.util.jsf;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class FacesUtil implements Serializable {

    public static boolean isPostback() {
        return FacesContext.getCurrentInstance().isPostback();
    }

    public static boolean isNotPostback() {
        return !isPostback();
    }

    private static void add(String message, Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(message);
        msg.setSeverity(severity);
        context.addMessage(null, msg);
    }

    public static void info(String message) {
        add(message, FacesMessage.SEVERITY_INFO);
    }

    public static void error(String message) {
        add(message, FacesMessage.SEVERITY_ERROR);
    }

}
