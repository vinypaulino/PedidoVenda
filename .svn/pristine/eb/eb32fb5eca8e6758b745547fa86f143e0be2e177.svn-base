/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.naja.rn;

import com.naja.model.TarefaPrioridade;
import com.naja.reposity.TarefaPrioridades;
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
 * @author Lemoel Marques lemoel@gmail.com 
 * NajaSoftware
 */
public class TarefaPrioridadeRN extends RegraNegocio implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private TarefaPrioridades tarefaPrioridades;
    
    public List<TarefaPrioridade> todas() {
        try {
            return tarefaPrioridades.todas();
        } catch (DAOException ex) {
            Logger.getLogger(TarefaPrioridadeRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Transactional
    public void salvar(TarefaPrioridade tarefaPrioridade) {
        try {
            tarefaPrioridades.salvar(tarefaPrioridade);
        } catch (DAOException ex) {
            Logger.getLogger(TarefaPrioridadeRN.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Transactional
    public void deletar(TarefaPrioridade tarefaPrioridade) {
        try {
            tarefaPrioridades.deletar(tarefaPrioridade);
            info("Prioridade deletado com sucesso");
        } catch (DAOException ex) {
            Logger.getLogger(TarefaPrioridadeRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
