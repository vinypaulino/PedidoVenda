/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.model.Grupo;
import com.naja.model.Usuario;
import com.naja.reposity.Grupos;
import com.naja.reposity.Usuarios;
import com.naja.util.DAOException;
import com.naja.util.RegraNegocio;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class UsuarioRN extends RegraNegocio implements Serializable {

    @Inject
    private Usuarios usuarios;

    @Inject
    private Grupos grupos;

    public void salvar(Usuario usuario) {
        try {
            usuario.setPrimeiroNome(StringUtils.capitalize(usuario.getPrimeiroNome()));
            usuario.setNome(StringUtils.capitalize(usuario.getNome()));

            usuario.setSenha(toMD5(usuario.getSenha()));
            usuarios.salvar(usuario);
        } catch (DAOException ex) {
            Logger.getLogger(UsuarioRN.class.getName()).log(Level.SEVERE, null, ex);
            info("Erro ao salvar usuário");
        }                        
    }
    
    public Usuario usuarioLogado(Long id){
        return usuarios.usuarioLogado(id);    
    }
    
    public List<Usuario> todosAtivo() {
        try {
            return usuarios.todosAtivo();
        } catch (DAOException ex) {
            Logger.getLogger(UsuarioRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deletar(Usuario usuario) {
        try {
            //Desativando usuario
            usuario.setStatus(false);
            usuarios.deletar(usuario);
        } catch (DAOException ex) {
            Logger.getLogger(ProjetoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String toMD5(String senha) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        sen = hash.toString(16);
        return sen;
    }

    public List<Usuario> todosAtendente() {
        try {
            //Buscando o grupo SUPORTE id = 4
            Grupo g = grupos.porId(new Long(4));
            return g.getUsuarios();
        } catch (DAOException ex) {
            Logger.getLogger(UsuarioRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Usuario porId(Long id) {
        try {
            return usuarios.porId(id);
        } catch (DAOException ex) {
            Logger.getLogger(UsuarioRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
