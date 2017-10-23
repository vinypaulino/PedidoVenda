/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.model.Produto;
import com.naja.reposity.Produtos;
import com.naja.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author VinyPaulino
 */


public class ProdutoRN implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Inject
    private Produtos produtos;
    
    
    @Transactional
    public Produto salvar(Produto produto) {
//        Produto produtoExistente = produtos.porCodigoProduto(produto.getCodigoProduto());
//        
//        if (produtoExistente != null && !produtoExistente.equals(produto)){
//            throw new NegocioException("Já existe um produto com o Código informado");
//        }
//        
        return produtos.guardar(produto);
    }         
                
}
