/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.model.Categoria;
import com.naja.model.Produto;
import com.naja.reposity.Categorias;
import com.naja.rn.ProdutoRN;
import com.naja.util.jsf.FacesUtil;
import static com.naja.util.jsf.FacesUtil.info;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 *
 * @author VinyPaulino
 */
@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Categorias categorias;

    @Inject
    private ProdutoRN cadastroProdutoRN;
    @Inject
    private Produto produto;

    private Categoria categoriaPai;

    private List<Categoria> categoriasRaizes = new ArrayList();
    private List<Categoria> subCategorias = new ArrayList();

    public CadastroProdutoBean() {
        limpar();
    }

    public void inicializar() {

        if (FacesUtil.isNotPostback()) {
            categoriasRaizes = categorias.raizes();

            if (this.categoriaPai != null) {
                carregarSubcategorias();
            }
        }
    }

    public void carregarCategoriaPai() {
        categoriasRaizes = categorias.raizes();
    }

    public void carregarSubcategorias() {
        subCategorias = categorias.subcategoriasDe(categoriaPai);
    }

    private void limpar() {
        produto = new Produto();
        categoriaPai = null;
        subCategorias = new ArrayList();
    }

    public void salvar() {
        this.produto = cadastroProdutoRN.salvar(this.produto);
        limpar();

        info("Produto salvo com sucesso!");
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;

        if (this.produto != null) {
            this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
        }
    }

    public List<Categoria> getCategoriasRaizes() {
        return categoriasRaizes;
    }

    @NotNull
    public Categoria getCategoriaPai() {
        return categoriaPai;
    }

    public void setCategoriaPai(Categoria categoriaPai) {
        this.categoriaPai = categoriaPai;
    }

    public List<Categoria> getSubCategorias() {
        return subCategorias;
    }

    public boolean isEditando() {
        return this.produto.getId() != null;
    }
}
