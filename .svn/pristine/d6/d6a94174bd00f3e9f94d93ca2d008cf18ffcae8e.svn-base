/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.model.Tarefa;
import com.naja.rn.TarefaRN;
import com.naja.util.Controller;
import com.naja.util.RNException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Named
@ViewScoped
public class CadTarefaController extends Controller implements Serializable {

    @Inject
    private Tarefa tarefa;
    
    @Inject
    private TarefaRN tarefaRN;
    
    @PostConstruct
    public void init() {
        tarefa = new Tarefa();
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    

    public void salvar() {
        try {
            tarefaRN.salvar(tarefa);
        } catch (RNException ex) {
            Logger.getLogger(CadTarefaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletar() {
        
    }

}
