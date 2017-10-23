package com.naja.rn;

import com.naja.reposity.LogDAO;
import com.naja.model.Log;
import com.naja.util.DAOException;
import com.naja.util.RNException;
import com.naja.util.RegraNegocio;
import com.naja.util.jpa.Transactional;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

public class LogRN extends RegraNegocio implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Inject
    private LogDAO logDAO;

    @Transactional
    public void salvar(Log log) throws RNException {
                
        try {
            this.logDAO.salvar(log);            
        } catch (DAOException ex) {
            Logger.getLogger(LogRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
