/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.reposity;

/**
 *
 * @author Naja
 */
import com.naja.enumerator.StatusPedido;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.naja.model.Pedido;
import com.naja.filter.PedidoFilter;
import com.naja.model.Usuario;
import com.naja.util.Util;
import org.hibernate.Session;

public class Pedidos extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<Pedido> filtradosAdm() {
        Criteria crit = Util.getCriteria(this.em, Pedido.class);
        crit.add(Restrictions.eq("status", StatusPedido.EMITIDO));
        return crit.list();
    }

    public List<Pedido> filtradosCliente(Usuario cliente) {
        Criteria crit = Util.getCriteria(this.em, Pedido.class);
        crit.add(Restrictions.eq("cliente", cliente));
        return crit.list();
    }

    public List<Pedido> filtrados(PedidoFilter filtro, boolean isAdministrador, Usuario usuario) {

        Criteria criteria = Util.getCriteria(this.em, Pedido.class)
                // fazemos uma associação (join) com cliente e nomeamos como "c"
                .createAlias("cliente", "c");

        if (filtro.getNumeroDe() != null) {
            // id deve ser maior ou igual (ge = greater or equals) a filtro.numeroDe
            criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
        }

        if (filtro.getNumeroAte() != null) {
            // id deve ser menor ou igual (le = lower or equal) a filtro.numeroDe
            criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
        }

        if (filtro.getDataCriacaoDe() != null) {
            criteria.add(Restrictions.ge("dataCriacao", filtro.getDataCriacaoDe()));
        }

        if (filtro.getDataCriacaoAte() != null) {
            criteria.add(Restrictions.le("dataCriacao", filtro.getDataCriacaoAte()));
        }

        if (StringUtils.isNotBlank(filtro.getNomeCliente())) {
            // acessamos o nome do cliente associado ao pedido pelo alias "c", criado anteriormente
            criteria.add(Restrictions.ilike("c.primeiroNome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
        }

        if (isAdministrador == false) {
            criteria.add(Restrictions.eq("cliente", usuario));
        }

        if (filtro.getStatuses() != null && filtro.getStatuses().length > 0) {
            // adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
            criteria.add(Restrictions.in("status", filtro.getStatuses()));
        }

        return criteria.addOrder(Order.asc("id")).list();
    }

    public Pedido guardar(Pedido pedido) {

        Session session = this.em.unwrap(Session.class);
        session.saveOrUpdate(pedido);
        return pedido;
    }

    public Pedido porId(Long id) {
        return this.em.find(Pedido.class, id);
    }
}
