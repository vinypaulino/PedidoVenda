/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.enumerator;

/**
 *
 * @author Naja
 */
public enum FormaPagamento {
    DINHEIRO("Dinheiro"),
    CARTAO_CREDITO("Cartão de crédito"), 
    CARTAO_DEBITO("Cartão de débito"),
    CHEQUE("Cheque"),
    BOLETO_BANCARIO("Boleto bancário"),
    DEPOSITO_BANCARIO("Depósito bancário");
    
    private String descricao;
    
    FormaPagamento(String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
}
