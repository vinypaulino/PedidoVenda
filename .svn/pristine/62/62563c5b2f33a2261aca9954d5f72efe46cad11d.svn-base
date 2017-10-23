package com.naja.reposity;

import com.naja.model.Evento;
import com.naja.util.DAOException;
import com.naja.util.Util;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class Eventos extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<Evento> todos() throws DAOException {
        List<Evento> crit = Util.getCriteria(this.em, Evento.class)
                .addOrder(Order.asc("descricao"))
                .list();
        return crit;
    }

    public Evento porId(Long id) throws DAOException {
        return this.em.find(Evento.class, id);
    }

}
