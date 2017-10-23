/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.converter;

import com.naja.model.Pessoa;
import com.naja.reposity.Pessoas;
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
@FacesConverter(value = "pessoaConverter")
public class PesConverter implements Converter {

    @Inject
    private Pessoas pessoas;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Pessoa retorno = null;

        if (value != null) {
            try {
                Long id = Long.valueOf(value);
                retorno = this.pessoas.porId(id);
            } catch (DAOException ex) {
                Logger.getLogger(PesConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && (!value.toString().trim().equals("0"))) {
            Long id = ((Pessoa) value).getId();
            String retorno = (id == null ? null : id.toString());
            return retorno;
        }
        return "";
    }
}
