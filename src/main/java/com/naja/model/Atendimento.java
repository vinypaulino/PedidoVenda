/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.model;

import com.naja.enumerator.AtendimentoStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Entity
@Table(name = "ATENDIMENTOS")
public class Atendimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToMany(mappedBy = "atendimento", cascade = {CascadeType.ALL})
    private List<Anotacao> anotacoes;
    
    @OneToMany(mappedBy = "atendimento", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Anexo> anexos;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "TELEFONISTA_ID", nullable = false)
    @NotNull
    private Usuario telefonista;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ATENDENTE_ID")
    private Usuario atendente;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_ATENDIMENTO")
    @NotNull
    private Date dataAtendimento;

    @Temporal(TemporalType.TIME)
    @Column(name = "HORA_ATENDIMENTO", nullable = false)
    @NotNull
    private Date horaAtendimento;

    @Column(name = "STATUS", nullable = false,length = 2)
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private AtendimentoStatus status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    @NotNull
    private Pessoa cliente;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "ATENDIMENTOS_EVENTOS",joinColumns = @JoinColumn(name = "ATENDIMENTO_ID"),
            inverseJoinColumns = @JoinColumn(name = "EVENTO_ID"))
    private List<Evento> eventos = new ArrayList<>();
    
    @Column(name = "FALAR_COM")
    private String falarCom;

    public AtendimentoStatus getStatus() {
        return status;
    }

    public void setStatus(AtendimentoStatus status) {
        this.status = status;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Usuario getAtendente() {
        return atendente;
    }

    public void setAtendente(Usuario atendente) {
        this.atendente = atendente;
    }

    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }

    public Usuario getTelefonista() {
        return telefonista;
    }

    public void setTelefonista(Usuario telefonista) {
        this.telefonista = telefonista;
    }

    public Date getHoraAtendimento() {
        return horaAtendimento;
    }

    public void setHoraAtendimento(Date horaAtendimento) {
        this.horaAtendimento = horaAtendimento;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public String getFalarCom() {
        return falarCom;
    }

    public void setFalarCom(String falarCom) {
        this.falarCom = falarCom;
    }

    public List<Anexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<Anexo> anexos) {
        this.anexos = anexos;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Atendimento other = (Atendimento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
