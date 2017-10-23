/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.converter;

import com.naja.model.Grupo;
import com.naja.reposity.Grupos;
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
@FacesConverter(forClass = Grupo.class, value = "grupoConverter")
public class GrupoConverter implements Converter {

    @Inject
    private Grupos grupos;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Grupo retorno = null;

        if (value != null) {
            try {
                retorno = this.grupos.porId(new Long(value));
            } catch (DAOException ex) {
                Logger.getLogger(GrupoConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long id = ((Grupo) value).getId();
            String retorno = (id == null ? null : id.toString());
            return retorno;
        }
        return "";
    }
}