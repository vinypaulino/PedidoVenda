package com.naja.reposity;

import com.naja.model.Grupo;
import com.naja.model.Usuario;
import com.naja.util.DAOException;
import com.naja.util.Util;
import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

public class Usuarios extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public Usuario porId(Long id) throws DAOException {
        return this.em.find(Usuario.class, id);
    }

    public List<Usuario> clientes() {
        // TODO filtrar apenas vendedores (por um grupo específico)
        return this.em.createQuery("from Usuario", Usuario.class)
                .getResultList();
    }

    public Usuario porEmail(String email) {
        Usuario usuario = null;

        try {
            usuario = this.em.createQuery("from Usuario where lower(email) = :email", Usuario.class)
                    .setParameter("email", email.toLowerCase()).getSingleResult();
        } catch (NoResultException e) {
            // nenhum usuário encontrado com o e-mail informado
        }

        return usuario;
    }

    public void salvar(Usuario usuario) throws DAOException {

        try {
            Session session = this.em.unwrap(Session.class);
            session.saveOrUpdate(usuario);
        } catch (ConstraintViolationException e) {
            throw new DAOException("Usuário já cadastrada.", e);
        }
    }

    public Usuario usuarioLogado(Long id) {
        TypedQuery<Usuario> query = this.em.createQuery(
                "from Usuario u join fetch u.grupos g "
                        + "where u.id = :id", Usuario.class);
        query.setParameter("id", id);                 
        Usuario usuario = query.getSingleResult();
        return usuario;
    }

    public List<Usuario> todosAtivo() throws DAOException {
        List<Usuario> crit = Util.getCriteria(this.em, Usuario.class)
                .addOrder(Order.asc("nome"))
                .add(Restrictions.eq("status", true))
                .list();
        return crit;
    }

    //Desativando o usuario, usuario nunca pode ser apagado, para
    //Poder guardar os históricos dele
    public void deletar(Usuario usuario) throws DAOException {
        Session session = this.em.unwrap(Session.class);
        session.saveOrUpdate(usuario);
        //this.em.remove(this.em.getReference(Usuario.class, usuario.getId()));
    }

}
