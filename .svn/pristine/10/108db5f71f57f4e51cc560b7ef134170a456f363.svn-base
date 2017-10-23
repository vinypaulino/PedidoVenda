/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.model;

import com.naja.enumerator.AnotacaoTipo;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Entity
@Table(name = "ANOTACOES")
public class Anotacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ATENDENTE_ID",nullable = false)
    @NotNull
    private Usuario atendente;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ATENDIMENTO_ID",nullable = false)
    @NotNull
    private Atendimento atendimento;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_ANOTACAO", nullable = false)
    @NotNull
    private Date dataAnotacao;
    
    @Temporal(TemporalType.TIME)
    @Column(name = "HORA_ANOTACAO", nullable = false)
    @NotNull
    private Date horaAnotacao;

    @Column(name = "DESCRICAO", nullable = false)
    @NotNull
    private String descricao;
    
    @Column(name = "ANOTACAO_TIPO",length = 25, nullable = false)
    @Enumerated (EnumType.STRING)
    private AnotacaoTipo anotacaoTipo;
    
    @Column(name = "FALOU_COM",nullable = false)
    private String falouCom;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;        
    }

    public Date getDataAnotacao() {
        return dataAnotacao;
    }

    public void setDataAnotacao(Date dataAnotacao) {
        this.dataAnotacao = dataAnotacao;
    }

    public Usuario getAtendente() {
        return atendente;
    }

    public void setAtendente(Usuario atendente) {
        this.atendente = atendente;
    }

    public Date getHoraAnotacao() {
        return horaAnotacao;
    }

    public void setHoraAnotacao(Date horaAnotacao) {
        this.horaAnotacao = horaAnotacao;
    }
    
    public AnotacaoTipo getAnotacaoTipo() {
        return anotacaoTipo;
    }

    public void setAnotacaoTipo(AnotacaoTipo anotacaoTipo) {
        this.anotacaoTipo = anotacaoTipo;
    }

    public String getFalouCom() {
        return falouCom;
    }

    public void setFalouCom(String falouCom) {
        this.falouCom = falouCom;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Anotacao other = (Anotacao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}