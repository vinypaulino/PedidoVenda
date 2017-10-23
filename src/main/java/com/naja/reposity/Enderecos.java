/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.reposity;

import com.naja.model.Endereco;
import com.naja.model.Evento;
import com.naja.util.DAOException;
import com.naja.util.Util;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Naja
 */
public class Enderecos extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<Endereco> todos() throws DAOException {

        List<Endereco> crit = Util.getCriteria(this.em, Evento.class)
                .addOrder(Order.asc("descricao"))
                .list();
        return crit;

    }
}
