/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.rn.ProjetoRN;
import com.naja.model.Departamento;
import com.naja.model.Endereco;
import com.naja.model.Grupo;
import com.naja.model.Projeto;
import com.naja.rn.UsuarioRN;
import com.naja.model.Usuario;
import com.naja.rn.DepartamentoRN;
import com.naja.rn.EnderecoRN;

import com.naja.rn.GrupoRN;
import com.naja.util.Controller;
import com.naja.util.DAOException;
import com.naja.util.jpa.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Named
@ViewScoped
public class CadUsuarioController extends Controller implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuario usuario;

    @Inject
    private Grupo grupo;

    @Inject
    private UsuarioRN usuarioRN;

    @Inject
    private GrupoRN grupoRN;

    @Inject
    private EnderecoRN enderecoRN;

    private List<Grupo> grupoList;
    private List<Usuario> usuarioList;
    private String confirmaSenha;
    private String descricaoEndereco;
    private String nomeRua;
    private String numero;
    private String complemento;
    private String cep;

    @PostConstruct
    public void init() {
        if (isAdministrador()) {
            usuario = new Usuario();
            usuario.setStatus(true);
            usuarioList = usuarioRN.todosAtivo();
        } else {          
            this.usuario = usuarioRN.porId(contextoBean.getUsuario().getId());            
        }
    }

    public List<Endereco> getEnderecos() throws DAOException {
        return enderecoRN.buscar();
    }

//    public List<Endereco> getEnderecosDoUsuario() {
//        return this.usuario.getEnderecos();
//    }
    public void novoEndereco() {
        Endereco endereco = new Endereco();
    }

    @Transactional
    public void salvar() {

        if (isAdministrador()) {
            this.usuario.setDtCadastro(new Date());
        }

//        grupoList = null;
//        grupoList = grupoRN.gruposDoUsuario(usuario);        
        usuario = usuarioRN.usuarioLogado(contextoBean.getUsuario().getId());
        this.usuario.setDtCadastro(new Date());
        usuario.setGrupos(grupoRN.gruposDoUsuario(usuario));
        usuarioRN.salvar(this.usuario);
        init();
        info("Usuário Salvo com sucesso");
    }

    @Transactional
    public void deletar() {
        usuarioRN.deletar(usuario);
        init();
        info("Usuario Deletado com sucesso");
    }

    public void adicionarEndereco() {
        Endereco endereco = new Endereco();
        endereco.setDescricaoEndereco(descricaoEndereco);
        endereco.setNomeRua(nomeRua);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setCep(cep);
        endereco.setUsuario(this.usuario);
//        this.usuario.getEnderecos().add(endereco);

        descricaoEndereco = null;
        nomeRua = null;
        numero = null;
        complemento = null;
        cep = null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Grupo> getGrupoList() {

        if (grupoList == null) {
            grupoList = grupoRN.todos();
        }
        return grupoList;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getDescricaoEndereco() {
        return descricaoEndereco;
    }

    public void setDescricaoEndereco(String descricaoEndereco) {
        this.descricaoEndereco = descricaoEndereco;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean isAdministrador() {
        Usuario usuario = contextoBean.getUsuario();

        List<Grupo> grupos = usuario.getGrupos();

        for (Grupo gp : grupos) {
            if (gp.getDescricao().equals("Administradores")) {
                return true;
            }
        }
        return false;
    }

}
