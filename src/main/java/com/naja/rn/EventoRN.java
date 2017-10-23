/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.model.Evento;
import com.naja.reposity.Eventos;
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
public class EventoRN extends RegraNegocio implements Serializable {

    @Inject
    private Eventos eventos;

    public List<Evento> todos() {
        try {
            return eventos.todos();
        } catch (DAOException ex) {
            Logger.getLogger(EventoRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}