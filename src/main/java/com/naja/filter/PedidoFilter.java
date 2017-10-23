/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.filter;

/**
 *
 * @author VinyPaulino
 */
import com.naja.enumerator.StatusPedido;
import java.io.Serializable;
import java.util.Date;

public class PedidoFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long numeroDe;
    private Long numeroAte;
    private Date dataCriacaoDe;
    private Date dataCriacaoAte;
    private String nomeCliente;
    private StatusPedido[] statuses;

    public Long getNumeroDe() {
        return numeroDe;
    }

    public void setNumeroDe(Long numeroDe) {
        this.numeroDe = numeroDe;
    }

    public Long getNumeroAte() {
        return numeroAte;
    }

    public void setNumeroAte(Long numeroAte) {
        this.numeroAte = numeroAte;
    }

    public Date getDataCriacaoDe() {
        return dataCriacaoDe;
    }

    public void setDataCriacaoDe(Date dataCriacaoDe) {
        this.dataCriacaoDe = dataCriacaoDe;
    }

    public Date getDataCriacaoAte() {
        return dataCriacaoAte;
    }

    public void setDataCriacaoAte(Date dataCriacaoAte) {
        this.dataCriacaoAte = dataCriacaoAte;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public StatusPedido[] getStatuses() {
        return statuses;
    }

    public void setStatuses(StatusPedido[] statuses) {
        this.statuses = statuses;
    }

}
