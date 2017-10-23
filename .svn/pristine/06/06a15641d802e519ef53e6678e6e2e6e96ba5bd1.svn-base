/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.enumerator.AnotacaoTipo;
import com.naja.model.Anotacao;
import com.naja.model.Atendimento;
import com.naja.reposity.Anotacoes;
import com.naja.util.DAOException;
import com.naja.util.RegraNegocio;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class AnotacaoRN extends RegraNegocio implements Serializable {

    @Inject
    private Anotacoes anotacoes;

    public List<Anotacao> todos(Atendimento atendimento, AnotacaoTipo anotacaoTipo) {
        try {
            return anotacoes.todos(atendimento, anotacaoTipo);
        } catch (DAOException ex) {
            Logger.getLogger(GrupoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Anotacao> todasAnotacaoAtendimento (Atendimento atendimento) {
        try {
            return anotacoes.todasAnotacaoAtendimento(atendimento);
        } catch (DAOException ex) {
            Logger.getLogger(GrupoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
