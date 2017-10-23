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
public enum StatusPedido {
    ORCAMENTO("Orçamento"),
    EMITIDO("Emitido"),
    ENTREGA("Enviado para Entrega"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");
    
    private String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
