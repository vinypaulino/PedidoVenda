/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.reposity;

import com.naja.filter.ProdutoFilter;
import com.naja.model.Produto;
import com.naja.service.NegocioException;

import com.naja.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author VinyPaulino
 */
public class Produtos extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Produto guardar(Produto produto) {
        produto = manager.merge(produto);
        return produto;
    }

    public List<Produto> filtrados(ProdutoFilter filtro) {

        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Produto.class);

        if (StringUtils.isNotBlank(filtro.getCodigoProduto())) {
            criteria.add(Restrictions.eq("codigoProduto", filtro.getCodigoProduto()));
        }
        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Produto porId(Long id) {
        return manager.find(Produto.class, id);
    }

    public Produto porCodigoProduto(String codigoProduto) {
        try {
            return this.em.createQuery("from Produto where upper(codigoProduto) = :codigoProduto", Produto.class)
                    .setParameter("codigoProduto", codigoProduto.toUpperCase())
                    .getSingleResult();

        } catch (NegocioException e) {
            return null;
        }
    }

    //retorna os produtos por nome digitados no Dialog 
    public List<Produto> porNomeSemelhante(String nome) {
        return manager.createQuery("from Produto where nome like :nome", Produto.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Transactional
    public void remover(Produto produto) {
        try {
            produto = porId(produto.getId());
            manager.remove(produto);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Produto não pode ser excluido");
        }
    }

    public List<Produto> porNome(String nome) {
        return this.manager.createQuery("from Produto where upper(nome) like :nome", Produto.class)
                .setParameter("nome", nome.toUpperCase() + "%").getResultList();
    }

    public List<Produto> produtos() {
        return this.em.createQuery("from Produto", Produto.class)
                .getResultList();
    }
}
