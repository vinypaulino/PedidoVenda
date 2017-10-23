/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.naja.rn;

import com.naja.model.Departamento;
import com.naja.model.Usuario;
import com.naja.reposity.Departamentos;
import com.naja.util.DAOException;
import com.naja.util.RegraNegocio;
import com.naja.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com 
 * NajaSoftware
 */
public class DepartamentoRN extends RegraNegocio implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Departamentos departamentos;
            
    @Transactional
    public void salvar(Departamento departamento){
        try {
            departamentos.salvar(departamento);
        } catch (DAOException ex) {
            Logger.getLogger(DepartamentoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Departamento> todos() {
        try {
            return departamentos.todos();
        } catch (DAOException ex) {
            Logger.getLogger(DepartamentoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Transactional
    public void deletar(Departamento departamento) {
        try {
            List<Usuario> usuarioList = departamentos.porId(departamento.getId()).getUsuarios();
            
            if(usuarioList.isEmpty()) {
                departamentos.deletar(departamento);
                info("Departamento apagado com sucesso");
            } else {
                error("Este departamento não pode ser apagado, já contém usuários! ");
            }
            
        } catch (DAOException ex) {
            Logger.getLogger(DepartamentoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
