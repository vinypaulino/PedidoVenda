/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.enumerator;

import java.io.Serializable;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public enum AtendimentoStatus implements Serializable {

    AG_ATENDIMENTO("Ag Atend"),
    ATENDENDO("Atendendo"),
    ATENDIDO("Atendido"),
    FECHADO("Fechado"),
    AG_ANALISE("Ag Análise"),
    EM_ANALISE("Em Análise"),
    ANALISADO("Analisado");

    private String descricao;
    private String statusColor;

    /**
     * Construtor privado para montar a enum
     */
    private AtendimentoStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatusColor() {

        if (equals(this.AG_ATENDIMENTO)) {
            return "w3-pale-red";
        }

        if (equals(this.ATENDENDO)) {
            return "w3-pale-yellow";
        }

        if (equals(this.ATENDIDO)) {
            return "w3-pale-green";
        }

        if (equals(this.FECHADO)) {
            return "w3-pale-green";
        }

        if (equals(this.AG_ANALISE)) {
            return "w3-blue";
        }

        if (equals(this.EM_ANALISE)) {
            return "w3-pale-green";
        }

        if (equals(this.ANALISADO)) {
            return "w3-pale-green";
        }

        return null;
    }

}
