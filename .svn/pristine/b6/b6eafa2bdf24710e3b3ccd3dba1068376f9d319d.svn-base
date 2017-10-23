/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.reposity;

import com.naja.model.TarefaAtividade;
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
public class TarefaAtividades extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<TarefaAtividade> todas() throws DAOException {
        List<TarefaAtividade> crit = Util.getCriteria(this.em, TarefaAtividade.class)
                .addOrder(Order.desc("descricao"))
                .list();
        return crit;
    }

    public void salva(TarefaAtividade tarefaAtividade) throws DAOException {
        Session session = this.em.unwrap(Session.class);
        session.saveOrUpdate(tarefaAtividade);
    }

    public void deletar(TarefaAtividade tarefaAtividade) throws DAOException {
        Query query = this.em.createQuery("DELETE FROM TarefaAtividade t WHERE t.id = :id");
        query.setParameter("id", tarefaAtividade.getId());
        query.executeUpdate();
    }

}
