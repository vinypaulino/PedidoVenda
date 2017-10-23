/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.model.Projeto;
import com.naja.model.Usuario;
import com.naja.reposity.Projetos;
import com.naja.util.DAOException;
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
public class ProjetoRN extends RegraNegocio implements Serializable {

    @Inject
    private Projetos projetos;

    public List<Projeto> todos() {
        try {
            return projetos.todos();
        } catch (DAOException ex) {
            Logger.getLogger(ProjetoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Transactional
    public void salvar(Projeto projeto) {
        try {
            projetos.salvar(projeto);
        } catch (DAOException ex) {
            Logger.getLogger(ProjetoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Transactional
    public void deletar(Projeto projeto) {
        try {

            projetos.deletar(projeto);
            info("Projeto Deletado com sucesso");

        } catch (DAOException ex) {
            Logger.getLogger(ProjetoRN.class.getName()).log(Level.SEVERE, null, ex);
            error("Erro ao deletar");
        }
    }

}
