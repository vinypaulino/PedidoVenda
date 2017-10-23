/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.converter;

import com.naja.model.Projeto;
import com.naja.reposity.Projetos;
import com.naja.util.DAOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@FacesConverter(forClass = Projeto.class, value = "projetoConverter")
public class ProjetoConverter implements Converter {

    @Inject
    private Projetos projetos;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Projeto retorno = null;

        if (value != null) {
            try {
                retorno = this.projetos.porId(new Long(value));
            } catch (DAOException ex) {
                Logger.getLogger(ProjetoConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long id = ((Projeto) value).getId();
            String retorno = (id == null ? null : id.toString());
            return retorno;
        }
        return "";
    }
}