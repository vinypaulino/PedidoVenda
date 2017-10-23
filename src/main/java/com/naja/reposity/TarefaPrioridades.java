/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.reposity;

import com.naja.model.TarefaPrioridade;
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
public class TarefaPrioridades extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<TarefaPrioridade> todas() throws DAOException {
        List<TarefaPrioridade> crit = Util.getCriteria(this.em, TarefaPrioridade.class)
                .addOrder(Order.desc("descricao"))
                .list();
        return crit;
    }

    public void salvar(TarefaPrioridade tarefaPrioridade) throws DAOException {
        Session session = this.em.unwrap(Session.class);
        session.saveOrUpdate(tarefaPrioridade);
    }

    public void deletar(TarefaPrioridade tarefaPrioridade) throws DAOException {
        Query query = this.em.createQuery("DELETE FROM TarefaPrioridade t WHERE t.id = :id");
        query.setParameter("id", tarefaPrioridade.getId());
        query.executeUpdate();
    }
}
