/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.reposity;

import com.naja.model.Grupo;
import com.naja.model.Usuario;
import com.naja.util.DAOException;
import com.naja.util.Util;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware --- Editado por VinyPaulino
 */
public class Grupos extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<Grupo> todos() throws DAOException {
        List<Grupo> crit = Util.getCriteria(this.em, Grupo.class)
                .addOrder(Order.asc("nome"))
                .list();
        return crit;
    }

    public List<Grupo> gruposDoUsuario(Usuario usuario) throws DAOException {
        List<Grupo> crit = Util.getCriteria(this.em, Grupo.class)
                .add(Restrictions.eq("usuario", usuario))
                .list();
        return crit;
    }

    public Grupo porId(Long id) throws DAOException {
        return this.em.find(Grupo.class, id);
    }

    public void salvar(Grupo grupo) throws DAOException {
        Session session = this.em.unwrap(Session.class);
        session.saveOrUpdate(grupo);
    }

    public void deletar(Grupo grupo) throws DAOException {
        em.remove(em.getReference(Grupo.class, grupo.getId()));
    }

}
