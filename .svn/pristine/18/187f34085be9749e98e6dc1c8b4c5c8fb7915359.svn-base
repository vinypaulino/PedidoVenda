/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.model.Projeto;
import com.naja.rn.ProjetoRN;
import com.naja.util.Controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Named
@ViewScoped
public class CadProjetoController extends Controller implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ProjetoRN projetoRN;

    @Inject
    private Projeto projeto;

    private List<Projeto> projetoList;

    @PostConstruct
    public void init() {
        projeto = new Projeto();
        projetoList = projetoRN.todos();
    }    
    
    public void salvar() {
        projetoRN.salvar(projeto);
        info("Projeto Salvo com sucesso");
        init();        
    }

    public void deletar() {
        projetoRN.deletar(projeto);        
        init();        
    }

    public List<Projeto> getProjetoList() {
        return projetoList;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

}
