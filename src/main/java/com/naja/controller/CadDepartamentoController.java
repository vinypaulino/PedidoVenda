/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.model.Departamento;
import com.naja.rn.DepartamentoRN;
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
public class CadDepartamentoController extends Controller implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DepartamentoRN departamentoRN;

    @Inject
    private Departamento departamento;

    private List<Departamento> departamentoList;

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @PostConstruct
    public void init() {
        departamento = new Departamento();
        departamentoList = departamentoRN.todos();
    }
   
    public void salvar() {
        departamentoRN.salvar(departamento);
        init();
        info("Departamento Salvo com sucesso");
    }

    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }
    
    public void deletar() {
        departamentoRN.deletar(departamento);
        init();        
    }

}
