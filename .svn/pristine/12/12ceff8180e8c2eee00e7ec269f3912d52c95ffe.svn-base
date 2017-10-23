/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.naja.reposity;

import com.naja.model.TarefaEstado;
import com.naja.model.TarefaTipo;
import com.naja.util.DAOException;
import com.naja.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com 
 * NajaSoftware
 */
public class TarefaTipos extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<TarefaTipo> todas() throws DAOException {
        List<TarefaTipo> crit = Util.getCriteria(this.em, TarefaTipo.class)
                .addOrder(Order.desc("descricao"))
                .list();
        return crit;
    }    

    public void salvar(TarefaTipo tarefaTipo)throws DAOException {
       Session session = this.em.unwrap(Session.class);
       session.saveOrUpdate(tarefaTipo);
    }

    public void deletar(TarefaTipo tarefaTipo) {
        Query query = this.em.createQuery("DELETE FROM TarefaTipo t WHERE t.id = :id");
        query.setParameter("id", tarefaTipo.getId());
        query.executeUpdate();
    }
}