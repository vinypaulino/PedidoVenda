/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.reposity;

import com.naja.enumerator.AtendimentoStatus;
import com.naja.model.Atendimento;
import com.naja.util.DAOException;
import com.naja.util.Util;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class Atendimentos extends DAO implements Serializable {

    public void salvar(Atendimento atendimento) throws DAOException {
        Session session = this.em.unwrap(Session.class);
        session.saveOrUpdate(atendimento);
    }

    public List<Atendimento> todos(Date dataInicio, Date dataFim) throws DAOException {

        Criteria crit = Util.getCriteria(this.em, Atendimento.class);
        crit.addOrder(Order.desc("dataAtendimento"));
        crit.addOrder(Order.desc("horaAtendimento"));

        if (dataInicio != null) {
            crit.add(Restrictions.ge("dataAtendimento", dataInicio));
        }

        if (dataFim != null) {
            crit.add(Restrictions.le("dataAtendimento", dataFim));
        }
        //Between 
        //.add(Restrictions.between("date", startDate, endDate));

        return crit.list();
    }

    public Atendimento porId(Long id) throws DAOException {
        return this.em.find(Atendimento.class, id);
    }

    public void deletar(Atendimento atendimento) throws DAOException {
        em.remove(em.getReference(Atendimento.class, atendimento.getId()));
    }

    public List<Atendimento> localizar(Map filtros) {

        Criteria crit = Util.getCriteria(this.em, Atendimento.class);
        crit.addOrder(Order.desc("dataAtendimento"));
        crit.addOrder(Order.desc("horaAtendimento"));

        if (filtros.get("dtInicio") != null) {
            crit.add(Restrictions.ge("dataAtendimento", filtros.get("dtInicio")));
        }

        if (filtros.get("dtFim") != null) {
            crit.add(Restrictions.le("dataAtendimento", filtros.get("dtFim")));
        }

        if (filtros.get("cliente") != null) {
            crit.add(Restrictions.eq("cliente", filtros.get("cliente")));
        }

        if (filtros.get("atendente") != null) {
            crit.add(Restrictions.eq("atendente", filtros.get("atendente")));
        }

        if (filtros.get("status") != null) {
            crit.add(Restrictions.eq("status", filtros.get("status")));
        }

        return crit.list();
    }

    public List<Atendimento> buscaPorPaginacao(Map<String, Object> filtros, Map<String, Object> filtrosPrimefaces) {

        Criteria crit = criarCriteriaParaFiltro(filtros);

        crit.setFirstResult((int) filtros.get("posicaoPrimeiraLinha"));
        crit.setMaxResults((int) filtros.get("maximoPorPagina"));
        crit.createAlias("cliente", "cliente");
        crit.createAlias("atendente", "atendente", JoinType.LEFT_OUTER_JOIN);
        //crit.createAlias("anotacoes", "anotacoes", JoinType.LEFT_OUTER_JOIN);

        String ordenacao = (String) filtros.get("ordenacao");
        String ordernarPeloCampo = (String) filtros.get("ordernarPeloCampo");

        if (ordenacao.equals("ASCENDING") && StringUtils.isNotBlank(ordernarPeloCampo)) {
            crit.addOrder(Order.asc(ordernarPeloCampo));
        } else if (StringUtils.isNotBlank(ordernarPeloCampo)) {
            crit.addOrder(Order.desc(ordernarPeloCampo));
        } else {
            crit.addOrder(Order.desc("dataAtendimento"));
            crit.addOrder(Order.desc("horaAtendimento"));
        }

        if (filtros.get("id") != null && StringUtils.isNotBlank(filtros.get("id").toString())) {
            crit.add(Restrictions.eq("id", Long.parseLong(filtros.get("id").toString().trim())));
        }

        if (filtros.get("dtInicio") != null) {
            crit.add(Restrictions.ge("dataAtendimento", filtros.get("dtInicio")));
        }

        if (filtros.get("dtFim") != null) {
            crit.add(Restrictions.le("dataAtendimento", filtros.get("dtFim")));
        }
        
//        if (filtros.get("dtInicioAnotacao") != null) {
//            crit.add(Restrictions.ge("dataAtendimento", filtros.get("dtInicio")));
//        }
//
//        if (filtros.get("dtFimAnotacao") != null) {
//            crit.add(Restrictions.le("dataAtendimento", filtros.get("dtFim")));
//        }

        if (filtros.get("cliente") != null) {
            crit.add(Restrictions.eq("cliente", filtros.get("cliente")));
        }

        if (filtros.get("atendente") != null) {
            crit.add(Restrictions.eq("atendente", filtros.get("atendente")));
        }

        if (filtros.get("status") != null) {
            crit.add(Restrictions.eq("status", filtros.get("status")));
        }

        //Vem do primefaces usado no cabeçalho das tabelas para busca
        if (filtrosPrimefaces.get("status") != null) {
            for (AtendimentoStatus status : AtendimentoStatus.values()) {
                if (status.getDescricao().equalsIgnoreCase(filtrosPrimefaces.get("status").toString().trim())) {
                    crit.add(Restrictions.in("status", status));
                }
            }
        }

        if (filtrosPrimefaces.get("cliente.nomeFantasia") != null) {
            crit.add(Restrictions.like("cliente.nomeFantasia", "%" + filtrosPrimefaces.get("cliente.nomeFantasia").toString().trim() + "%"));
        }

        if (filtrosPrimefaces.get("atendente.primeiroNome") != null) {
            crit.add(Restrictions.like("atendente.primeiroNome", "%" + filtrosPrimefaces.get("atendente.primeiroNome").toString().trim() + "%"));
        }

        return crit.list();
    }

    public int countAll(Map filtros) {
        Criteria criteria = criarCriteriaParaFiltro(filtros);
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.uniqueResult()).intValue();
    }

    private Criteria criarCriteriaParaFiltro(Map filtros) {
        Criteria crit = Util.getCriteria(this.em, Atendimento.class);
        return crit;
    }

    public List<Atendimento> localizarSuporte(Map filtros) {

        Criteria crit = criarCriteriaParaFiltro(filtros);

        crit.createAlias("atendente", "atendente", JoinType.LEFT_OUTER_JOIN);
        crit.addOrder(Order.desc("dataAtendimento"));
        crit.addOrder(Order.desc("horaAtendimento"));

        Set<AtendimentoStatus> statusSet = (Set) filtros.get("status");
        if (filtros.get("status") != null) {
            crit.add(Restrictions.in("status", statusSet));
        }

        if (filtros.get("tipo") != null) {
            if (filtros.get("tipo").equals("my") && filtros.get("atendente.id") != null) {
                crit.add(Restrictions.eq("atendente.id", filtros.get("atendente.id")));
            }

            if (filtros.get("tipo").equals("ours")) {
                crit.add(Restrictions.isNull("atendente.id"));
            }

            if (filtros.get("tipo").equals("their")) {
                crit.add(Restrictions.isNotNull("atendente.id"));
                crit.add(Restrictions.ne("atendente.id", filtros.get("atendente.id")));
            }

        }
        return crit.list();
    }
}