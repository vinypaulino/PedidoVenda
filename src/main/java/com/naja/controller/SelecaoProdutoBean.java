/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.model.Produto;
import com.naja.reposity.Produtos;
import com.naja.util.Controller;
import com.naja.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Viny Paulino
 */
@Named
@ViewScoped
public class SelecaoProdutoBean extends Controller implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Produtos produtos;

    private String nome;

    private List<Produto> produtosFiltrados = new ArrayList<>();

    public void pesquisar() {
        produtosFiltrados = produtos.porNome(nome);
    }

    public void abrirDialogo() {

        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("maximizable", true);
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 350);
        opcoes.put("Height", 350);
        opcoes.put("width", 350);
        opcoes.put("contentWidth", 350);

        RequestContext.getCurrentInstance().openDialog("SelecaoProduto", opcoes, null);

    }

    public void inicializar() {
                      
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }
}
