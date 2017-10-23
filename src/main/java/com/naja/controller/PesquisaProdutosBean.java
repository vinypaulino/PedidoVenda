/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.filter.ProdutoFilter;
import com.naja.model.Produto;
import com.naja.reposity.Produtos;
import static com.naja.util.jsf.FacesUtil.info;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author VinyPaulino
 */
@Named
@ViewScoped
public class PesquisaProdutosBean  implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public PesquisaProdutosBean(){
  
    }
    @Inject
    private Produtos produtos;
    
    @Inject
    private ProdutoFilter filtro;
            
            
    private List<Produto> produtosFiltrados;
    
    private Produto produtoSelecionado;

    
    
    public PesquisaProdutosBean(ProdutoFilter filtro) {
        this.filtro = new ProdutoFilter();
    }    
    
    public void excluir(){
        produtos.remover(produtoSelecionado);
        produtosFiltrados.remove(produtoSelecionado);
        
        info("Produto " + produtoSelecionado.getCodigoProduto()
        + "excluido com sucesso");                               
    }
    
    public void pesquisar(){
        produtosFiltrados = produtos.filtrados(filtro);
    }
    
    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public ProdutoFilter getFiltro() {
        return filtro;
    }    

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }        
}