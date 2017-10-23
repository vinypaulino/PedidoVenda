package com.naja.model;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "LOGS")
public class Log implements Serializable {

    private static final long serialVersionUID = -7577746818338200560L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @NotNull
    @NotEmpty
    @Column(name = "DT_LOG")
    @Temporal(TemporalType.DATE)
    private Date dataLog;
    
    @NotNull
    @NotEmpty
    @Column(name = "HORA_LOG")
    @Temporal(TemporalType.TIME)
    private Date horaLog;

    @NotNull
    @NotEmpty
    @Column(name = "DESCRICAO")
    private String descricao;

    @NotNull
    @NotEmpty
    @Column(name = "IP")
    private String ip;

    
    @Column(name = "LINK_ACESSO")
    private String linkAcesso;

    public Integer getId() {
        return id;
    }

    public Date getDataLog() {
        return dataLog;
    }

    public void setDataLog(Date dataLog) {
        this.dataLog = dataLog;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLinkAcesso() {
        return linkAcesso;
    }

    public void setLinkAcesso(String linkAcesso) {
        this.linkAcesso = linkAcesso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getHoraLog() {
        return horaLog;
    }

    public void setHoraLog(Date horaLog) {
        this.horaLog = horaLog;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Log other = (Log) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
