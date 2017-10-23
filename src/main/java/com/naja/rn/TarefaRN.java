/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.model.Tarefa;
import com.naja.reposity.Tarefas;
import com.naja.util.DAOException;
import com.naja.util.RNException;
import com.naja.util.RegraNegocio;
import com.naja.util.jpa.Transactional;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class TarefaRN extends RegraNegocio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Tarefas tarefas;

    @Transactional
    public void salvar(Tarefa t) throws RNException {
        try {
            Calendar dataCadastro = new GregorianCalendar();
            t.setDataCadastro(dataCadastro.getTime());
            tarefas.salvar(t);
            info("Tarefa cadastrada com sucesso");
        } catch (DAOException ex) {
            Logger.getLogger(TarefaRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Transactional
    public void deletar(Tarefa tarefa) throws RNException {

    }

}
