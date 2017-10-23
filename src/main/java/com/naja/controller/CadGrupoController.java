/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.model.Grupo;
import com.naja.rn.GrupoRN;
import com.naja.util.Controller;
import com.naja.util.RNException;
import java.io.Serializable;
import java.util.List;
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
public class CadGrupoController extends Controller implements Serializable {

    @Inject
    private Grupo grupo;

    private List<Grupo> grupoList;

    @Inject
    private GrupoRN grupoRN;

    @PostConstruct
    public void init() {
        grupo = new Grupo();
        grupoList = grupoRN.todos();
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void salvar() {
        grupoRN.salvar(grupo);
        init();
    }
    
    public List<Grupo> getGrupoList() {
        return grupoList;
    }
    
    public void deletar() {
        try {
            grupoRN.deletar(grupo);
            init();
        } catch (RNException ex) {
            Logger.getLogger(CadGrupoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
