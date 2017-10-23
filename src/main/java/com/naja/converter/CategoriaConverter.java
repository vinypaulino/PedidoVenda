/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.converter;

import com.naja.model.Categoria;
import com.naja.reposity.Categorias;
import com.naja.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author VinyPaulino
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    private Categorias categorias;

    public CategoriaConverter() {
        categorias = CDIServiceLocator.getBean(Categorias.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Categoria retorno = null;
        if (value != null) {
            Long id = new Long(value);
            retorno = categorias.porId(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return ((Categoria) o).getId().toString();
        }

        return "";
    }
}
