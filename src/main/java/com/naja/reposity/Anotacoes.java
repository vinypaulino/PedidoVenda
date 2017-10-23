/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.reposity;

import com.naja.enumerator.AnotacaoTipo;
import com.naja.model.Anotacao;
import com.naja.model.Atendimento;
import com.naja.util.DAOException;
import com.naja.util.Util;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class Anotacoes extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public List<Anotacao> todos(Atendimento atendimento,AnotacaoTipo anotacaoTipo) throws DAOException {
        List<Anotacao> crit = Util.getCriteria(this.em, Anotacao.class)
                .add(Restrictions.eq("anotacaoTipo", anotacaoTipo))
                .add(Restrictions.eq("atendimento", atendimento))
                .addOrder(Order.desc("dataAnotacao"))
                .addOrder(Order.desc("horaAnotacao"))                
                .list();
        return crit;
    }
    
    public List<Anotacao> todasAnotacaoAtendimento (Atendimento atendimento) throws DAOException {
        List<Anotacao> crit = Util.getCriteria(this.em, Anotacao.class)                
                .add(Restrictions.eq("atendimento", atendimento))
                .addOrder(Order.desc("dataAnotacao"))
                .addOrder(Order.desc("horaAnotacao"))                
                .list();
        return crit;
    }    
}
