/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.converter;

import com.naja.model.Departamento;
import com.naja.reposity.Departamentos;
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
@FacesConverter(forClass = Departamento.class)
public class DepConverter implements Converter {

    @Inject
    private Departamentos departamentos;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Departamento retorno = null;

        if (value != null) {
            try {
                Long id = Long.valueOf(value);
                retorno = this.departamentos.porId(id);
            } catch (DAOException ex) {
                Logger.getLogger(DepConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Long id = ((Departamento) value).getId();
            String retorno = (id == null ? null : id.toString());
            return retorno;
        }
        return "";
    }
}