package com.naja.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false, length = 80, name = "NOME")
    private String nome;

    @NotNull
    @NotEmpty
    @Column(nullable = false, length = 80, name = "PRIMEIRO_NOME")
    private String primeiroNome;

    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true, length = 255, name = "EMAIL")
    private String email;

    @NotNull
    @NotEmpty
    @Column(nullable = false, length = 255, name = "SENHA")
    private String senha;
    
    @NotNull
    @NotEmpty
    @Column(nullable = false, length = 255, name = "TELEFONE1")
    private String telefone;
    
    @NotNull
    @NotEmpty
    @Column(nullable = false, length = 255, name = "TELEFONE2")
    private String telefone2;

    
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "USUARIO_GRUPO", joinColumns = @JoinColumn(name = "USUARIO_ID"),
            inverseJoinColumns = @JoinColumn(name = "GRUPO_ID"))
    private List<Grupo> grupos = new ArrayList<>();       

    @Inject
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "DEPARTAMENTO_ID")
    private Departamento departamento;

    @Column(name = "STATUS")
    private boolean status;

    @Column(name = "DT_CADASTRO")
    @Temporal(TemporalType.DATE)
    @NotNull    
    private Date dtCadastro;
    
    @Column(name = "DT_DELETE")
    @Temporal(TemporalType.DATE)
    private Date dtDelete;    
    
    @Column (name = "EM_ATENDIMENTO",columnDefinition = "boolean default false")
    private boolean emAtendimento;

//    @OneToMany (fetch = FetchType.EAGER, mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true )
//    private List<Endereco> enderecos = new ArrayList<>();
    
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Date getDtDelete() {
        return dtDelete;
    }

    public void setDtDelete(Date dtDelete) {
        this.dtDelete = dtDelete;
    }   

    public boolean isEmAtendimento() {
        return emAtendimento;
    }

    public void setEmAtendimento(boolean emAtendimento) {
        this.emAtendimento = emAtendimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

//    public List<Endereco> getEnderecos() {
//        return enderecos;
//    }
//
//    public void setEnderecos(List<Endereco> enderecos) {
//        this.enderecos = enderecos;
//    }              
//    
//    public void adicionaEndereco(Endereco endereco){
//        this.enderecos.add(endereco);  
//    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
