/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.model.TarefaAtividade;
import com.naja.reposity.TarefaAtividades;
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
public class TarefaAtividadeRN extends RegraNegocio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TarefaAtividades tarefaAtividades;

    public List<TarefaAtividade> todas() {
        try {
            return tarefaAtividades.todas();
        } catch (DAOException ex) {
            Logger.getLogger(TarefaAtividadeRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Transactional
    public void salvar(TarefaAtividade tarefaAtividade) {
        try {
            tarefaAtividades.salva(tarefaAtividade);
        } catch (DAOException ex) {
            Logger.getLogger(TarefaAtividadeRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Transactional
    public void deletar(TarefaAtividade tarefaAtividade) {
        try {
            tarefaAtividades.deletar(tarefaAtividade);
        } catch (DAOException ex) {
            Logger.getLogger(TarefaAtividadeRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
