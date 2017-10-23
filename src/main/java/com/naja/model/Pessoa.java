package com.naja.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "PESSOAS")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = -4058528207983393408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;
    
    @Column (name = "CNPJ")
    private String cnpj;

    @Column(name = "NOME", nullable = false)
    @NotNull
    @NotEmpty
    private String nome;
    
    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @Column(name = "EMAIL")
    private String email;

    @Column(length = 1, name = "STATUS")
    private boolean status;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_CADASTRO")
    private Date dataCadastro;
    
    @Temporal(TemporalType.TIME)
    @Column(name = "HORA_CADASTRO")
    private Date horaCadastro;

    @ElementCollection(targetClass = String.class)
    @JoinTable(
            name = "PESSOA_TELEFONE",
            uniqueConstraints = {
                @UniqueConstraint(columnNames = {"PESSOA_COD", "TELEFONE"})},
            joinColumns = @JoinColumn(name = "PESSOA_COD"))
    @Column(name = "TELEFONE", length = 50)
    private Set<String> telefone = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    private List<Atendimento> atendimentos;
    
    @OneToOne
    @JoinColumn (name = "ENDERECO_ID")
    private Endereco endereco;
    
    @Column (name = "INSCRICAO_ESTADUAL")
    private String inscricaoEstadual;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Date getHoraCadastro() {
        return horaCadastro;
    }

    public void setHoraCadastro(Date horaCadastro) {
        this.horaCadastro = horaCadastro;
    }

    public Set<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(Set<String> telefone) {
        this.telefone = telefone;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
