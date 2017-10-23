/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.filter;

import java.io.Serializable;

/**
 *
 * @author VinyPaulino
 */

public class ProdutoFilter implements Serializable {
    
    private static final long SerialVersionUID = 1L;    
    
  
    
    private String codigoProduto;
    private String nome;

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
   
    
}
