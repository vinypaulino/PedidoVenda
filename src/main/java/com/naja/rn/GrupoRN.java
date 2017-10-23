/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.model.Grupo;
import com.naja.model.Usuario;
import com.naja.reposity.Grupos;
import com.naja.util.DAOException;
import com.naja.util.RNException;
import com.naja.util.RegraNegocio;
import com.naja.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class GrupoRN extends RegraNegocio implements Serializable {

    @Inject
    private Grupos grupos;

    public List<Grupo> todos() {
        try {
            return grupos.todos();
        } catch (DAOException ex) {
            Logger.getLogger(GrupoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Grupo> gruposDoUsuario(Usuario usuario) {
        try {
            return grupos.gruposDoUsuario(usuario);

        } catch (DAOException ex) {
            Logger.getLogger(GrupoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Transactional
    public void salvar(Grupo grupo) {
        try {
            grupos.salvar(grupo);
            info("Grupo Salvo com sucesso");
        } catch (DAOException ex) {
            Logger.getLogger(GrupoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Transactional
    public void deletar(Grupo grupo) throws RNException {
        try {

            List<Usuario> grupoList = grupos.porId(grupo.getId()).getUsuarios();

            if (grupoList.isEmpty()) {
                grupos.deletar(grupo);
                info("Grupo deletadado com sucesso");
            } else {
                error("Grupo já pussui usuários vinculados");
            }

        } catch (DAOException ex) {
            Logger.getLogger(GrupoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Grupo porId(Long id) {
        try {
            return grupos.porId(id);
        } catch (DAOException ex) {
            Logger.getLogger(GrupoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
