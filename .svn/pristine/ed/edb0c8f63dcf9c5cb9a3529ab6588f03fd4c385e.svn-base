/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.model.TarefaEstado;
import com.naja.model.TarefaTipo;
import com.naja.reposity.TarefaTipos;
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
public class TarefaTipoRN extends RegraNegocio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TarefaTipos tarefaTipos;

    public List<TarefaTipo> todos() {
        try {
            return tarefaTipos.todas();
        } catch (DAOException ex) {
            Logger.getLogger(TarefaTipoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Transactional
    public void salvar(TarefaTipo tarefaTipo) {
        try {
            tarefaTipos.salvar(tarefaTipo);
            info("Tipo salvo com sucesso");
        } catch (DAOException ex) {
            Logger.getLogger(TarefaEstadoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Transactional
    public void deletar(TarefaTipo tarefaTipo) {
        tarefaTipos.deletar(tarefaTipo);
        info("Tipo deletado com sucesso");
    }
}
