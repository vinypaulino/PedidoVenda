/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.reposity;

import com.naja.model.TarefaEstado;
import com.naja.util.DAOException;
import com.naja.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class TarefaEstados extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<TarefaEstado> todas() throws DAOException {
        List<TarefaEstado> crit = Util.getCriteria(this.em, TarefaEstado.class)
                .addOrder(Order.desc("descricao"))
                .list();
        return crit;
    }

    public void salvar(TarefaEstado estado) throws DAOException {
        Session session = this.em.unwrap(Session.class);
        session.saveOrUpdate(estado);
    }

    public void deletar(TarefaEstado tarefaEstado) {
        Query query = this.em.createQuery("DELETE FROM TarefaEstado t WHERE t.id = :id");
        query.setParameter("id", tarefaEstado.getId());
        query.executeUpdate();
    }

}
