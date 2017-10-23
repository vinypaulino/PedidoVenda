package com.naja.reposity;

import com.naja.model.Log;
import org.hibernate.exception.ConstraintViolationException;

import com.naja.util.DAOException;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public class Logs implements LogDAO, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;
    
    @Override
    public void salvar(Log log) throws DAOException {
        
        log.setUsuario(manager.merge(log.getUsuario()));
        
        try {
            manager.persist(log);
        } catch (ConstraintViolationException e) {
            throw new DAOException("Erro de chave duplicada.", e);
        }
    }

}
