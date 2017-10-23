/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.model;

import com.naja.enumerator.FormaPagamento;
import com.naja.enumerator.StatusPedido;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author VinyPaulino
 *
 */
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date dataCriacao;
    private String observacao;
    private Date dataEntrega;
    private BigDecimal valorTotal = BigDecimal.ZERO;
    private StatusPedido status = StatusPedido.ORCAMENTO;
    private FormaPagamento formaPagamento;
    private Usuario cliente;
    private EnderecoEntrega enderecoEntrega;
    private List<ItemPedido> itens = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao", nullable = false)
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Column(columnDefinition = "text")
    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrega", nullable = false)
    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false, length = 20)
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    @Embedded
    public EnderecoEntrega getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public void recalcularValorTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedido item : this.getItens()) {
            if (item.getProduto() != null && item.getProduto().getId() != null) {
                total = total.add(item.getValorTotal());
            }
        }
        this.setValorTotal(total);
    }
      
    public void adicionarItemVazio() {
        if (this.isOrcamento()) {
            Produto produto = new Produto();

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setPedido(this);            
            this.getItens().add(0, item);
        }
    }
    
    @Transient
    public boolean isValorTotalNegativo(){
        return this.getValorTotal().compareTo(BigDecimal.ZERO) < 0;
    }
    

    @Transient
    public boolean isOrcamento() {
        return StatusPedido.ORCAMENTO.equals(this.getStatus());
    }

    public void removerItemVazio(){
        ItemPedido primeitoItem = this.getItens().get(0);
        
        if (primeitoItem != null && primeitoItem.getProduto().getId() == null){
            this.getItens().remove(0);
        }                
    }

    @Transient
    public boolean isNaoEmissivel() {
        return !this.isEmissivel();
    }
    @Transient 
    public boolean isEmissivel(){
        return this.isExistente() && this.isOrcamento();
    }
    
    @Transient
    public boolean isCancelado(){
        return StatusPedido.CANCELADO.equals(this.getStatus());
    }    
    
    @Transient
    public boolean isCancelavel(){
        return this.isExistente() && !this.isCancelado();
    }
    
    @Transient
    public boolean isNaoCancelavel(){
        return !this.isCancelavel();
    }
    
    
    @Transient
    public boolean isExistente(){
        return this.getId() != null;
    }
    
    @Transient
    public boolean isAlteravel(){
        return this.isOrcamento();
    }
    
    @Transient
    public boolean isNaoAlteravel() {
        return !this.isAlteravel();
    }
    
}
