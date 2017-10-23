/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.reposity;

import com.naja.model.Grupo;
import com.naja.model.Menu;
import com.naja.util.DAOException;
import com.naja.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class Menus extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<Menu> todosFilhos(Menu menuPai) throws DAOException {
        List<Menu> crit = Util.getCriteria(this.em, Menu.class)
                .add(Restrictions.eq("paiId", menuPai.getId()))
                .addOrder(Order.asc("nivel"))
                .list();
        return crit;
    }

    /*
    Esta busca tem que ser refeita, deve trazer apenas os menus que pertence ao 
    grupo passado por parâmetro
     */
    public List<Menu> todosFilhos(Menu menuPai, Grupo gp) throws DAOException {
        Query query = this.em.createQuery("select m from Menu m where m.paiId = :menuPai");
        query.setParameter("menuPai", menuPai.getId());
        //query.setParameter("grupo",gp);
        return query.getResultList();
    }

    public List<Menu> todosRoot(List<Grupo> grupos) throws DAOException {
        List<Menu> crit = Util.getCriteria(this.em, Menu.class)
                .add(Restrictions.isNull("paiId"))
                .addOrder(Order.asc("nivel"))
                .list();
        return crit;
    }

    public List<Menu> todosRoot() throws DAOException {
        List<Menu> crit = Util.getCriteria(this.em, Menu.class)
                .add(Restrictions.isNull("paiId"))
                .addOrder(Order.asc("nivel"))
                .list();
        return crit;
    }

    public void salvar(Menu menu) throws DAOException {
        try {
            
            Session session = this.em.unwrap(Session.class);                        
            session.saveOrUpdate(menu);
            
            for (Grupo gp : menu.getGrupos()){
                session.saveOrUpdate(gp);
            }
            
        } catch (ConstraintViolationException e) {
            throw new DAOException("Menu já cadastrada.", e);
        }
    }

    public Menu porId(Long id) throws DAOException {
        return this.em.find(Menu.class, id);
    }

}
