/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.naja.rn;

import com.naja.model.TarefaEstado;
import com.naja.reposity.TarefaEstados;
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
public class TarefaEstadoRN extends RegraNegocio implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Inject
    private TarefaEstados tarefaEstados;
    

    public List<TarefaEstado> todos() {
        try {
            return tarefaEstados.todas();
        } catch (DAOException ex) {
            Logger.getLogger(TarefaEstadoRN.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return null;        
    }

    @Transactional
    public void salvar(TarefaEstado tarefaEstado) {
        try {
            tarefaEstados.salvar(tarefaEstado);
            info("Tarefa salva com sucesso");
        } catch (DAOException ex) {
            Logger.getLogger(TarefaEstadoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Transactional
    public void deletar(TarefaEstado tarefaEstado) {
        tarefaEstados.deletar(tarefaEstado);
        info("Tarefa deletada com sucesso");
    }

}