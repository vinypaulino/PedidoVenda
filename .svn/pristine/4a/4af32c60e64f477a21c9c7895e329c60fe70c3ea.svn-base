/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.reposity;

import com.naja.model.Departamento;
import com.naja.util.DAOException;
import com.naja.util.Util;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class Departamentos extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public void salvar(Departamento departamento) throws DAOException {
        Session session = this.em.unwrap(Session.class);
        session.saveOrUpdate(departamento);
    }

    public List<Departamento> todos() throws DAOException {
        List<Departamento> crit = Util.getCriteria(this.em, Departamento.class)
                .addOrder(Order.asc("nome"))
                .list();
        return crit;
    }

    public void deletar(Departamento departamento)throws DAOException {
        this.em.remove(this.em.getReference(Departamento.class, departamento.getId()));
    }

    public Departamento porId(Long id) throws DAOException {
        return this.em.find(Departamento.class, id);        
    }

}
