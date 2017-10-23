package com.naja.util;

import com.naja.controller.ContextoBean;
import com.naja.util.jsf.FacesUtil;
import java.io.Serializable;
import javax.inject.Inject;

/**
 * @brief Classe Mae
 * @author Lemoel Marques <lemoel@gmail.com>
 * @date 05/02/2016 18:28:58 Deus seja Louvado.
 */
public class Mae implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected ContextoBean contextoBean;

    protected void setLog(String msg) {
        contextoBean.setLog(msg);
    }
    
    protected void error (String msg) {
        FacesUtil.error(msg);
    }
    
    protected void info (String msg) {
        FacesUtil.info(msg);
    }

}
