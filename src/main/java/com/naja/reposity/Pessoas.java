package com.naja.reposity;

import com.naja.model.Pessoa;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import com.naja.util.DAOException;
import com.naja.util.Util;
import java.io.Serializable;
import java.util.Map;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class Pessoas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    public void salvar(Pessoa pessoa) throws DAOException {
        try {
            this.em.persist(pessoa);
        } catch (ConstraintViolationException e) {
            throw new DAOException("Pessoa já cadastrada.", e);
        }
    }

    public void excluir(Pessoa pessoa) throws DAOException {
        this.em.remove(pessoa);
    }

    public List<Pessoa> todas() throws DAOException {
        List<Pessoa> crit = Util.getCriteria(this.em, Pessoa.class)
                .addOrder(Order.asc("nome"))
                .list();
        return crit;
    }

    public Pessoa porId(Long id) throws DAOException {
        return (Pessoa) this.em.find(Pessoa.class, id);
    }

    public Boolean isExiste(String cpfCnpj) throws DAOException {
        Query query = this.em.createQuery("select p.codigoFiscal from Pessoa p where p.codigoFiscal = :codigoFiscal");
        query.setParameter("codigoFiscal", cpfCnpj);

        try {
            cpfCnpj = (String) query.getSingleResult();
            return true;
        } catch (NoResultException nre) {
            return false;
        }

    }

    public List<Pessoa> buscaPorPaginacao(Map<String, Object> criteriosDaBusca, Map<String, Object> filtrosPrimefaces) {

        Criteria crit = criarCriteriaParaFiltro(criteriosDaBusca);

        crit.setFirstResult((int) criteriosDaBusca.get("posicaoPrimeiraLinha"));
        crit.setMaxResults((int) criteriosDaBusca.get("maximoPorPagina"));

        String ordenacao = (String) criteriosDaBusca.get("ordenacao");
        String ordernarPeloCampo = (String) criteriosDaBusca.get("ordernarPeloCampo");

        if (ordenacao.equals("ASCENDING") && StringUtils.isNotBlank(ordernarPeloCampo)) {
            crit.addOrder(Order.asc(ordernarPeloCampo));
        } else if (StringUtils.isNotBlank(ordernarPeloCampo)) {
            crit.addOrder(Order.desc(ordernarPeloCampo));
        } else {
            crit.addOrder(Order.desc("id"));
        }
        
        if (StringUtils.isNotBlank((String)criteriosDaBusca.get("id"))) {
            crit.add(Restrictions.eq("id", Long.parseLong(criteriosDaBusca.get("id").toString().trim())));
        }

        if (StringUtils.isNotBlank((String)criteriosDaBusca.get("nome"))){
            crit.add(Restrictions.like("nome", "%" + criteriosDaBusca.get("nome").toString().trim() + "%"));
        }

        if (StringUtils.isNotBlank((String)criteriosDaBusca.get("nomeFantasia"))){
            crit.add(Restrictions.like("nomeFantasia", "%" + criteriosDaBusca.get("nomeFantasia").toString().trim() + "%"));
        }
        
        if (StringUtils.isNotBlank((String)criteriosDaBusca.get("email"))){
            crit.add(Restrictions.like("email", "%" + criteriosDaBusca.get("email").toString().trim() + "%"));
        }
        
        if (StringUtils.isNotBlank((String)criteriosDaBusca.get("ie"))){
            crit.add(Restrictions.like("inscricaoEstadual", "%" + criteriosDaBusca.get("ie").toString().trim() + "%"));
        }
        
        if (StringUtils.isNotBlank((String)criteriosDaBusca.get("cnpj"))){
            crit.add(Restrictions.like("cnpj", "%" + criteriosDaBusca.get("cnpj").toString().trim() + "%"));
        }

        if (criteriosDaBusca.get("status") != null){
            crit.add(Restrictions.eq("status",criteriosDaBusca.get("status")));
        }

        if (filtrosPrimefaces.get("nomeFantasia") != null) {
            crit.add(Restrictions.like("nomeFantasia", "%" + filtrosPrimefaces.get("nomeFantasia").toString().trim() + "%"));
        }

        if (filtrosPrimefaces.get("primeiroNome") != null) {
            crit.add(Restrictions.like("primeiroNome", "%" + filtrosPrimefaces.get("primeiroNome").toString().trim() + "%"));
        }

        return crit.list();
    }

    public int countAll(Map filtros) {
        Criteria criteria = criarCriteriaParaFiltro(filtros);
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.uniqueResult()).intValue();
    }

    private Criteria criarCriteriaParaFiltro(Map filtros) {
        Criteria crit = Util.getCriteria(this.em, Pessoa.class);
        return crit;
    }
}
