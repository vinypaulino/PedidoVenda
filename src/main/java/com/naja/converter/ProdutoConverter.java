/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.converter;

import com.naja.model.Produto;
import com.naja.reposity.Produtos;
import com.naja.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Naja
 */
@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {
    	@Inject
	private Produtos produtos;
	
	public ProdutoConverter() {
		produtos = CDIServiceLocator.getBean(Produtos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;
		
		if (value != null) {
                    try {
                        Long id = new Long(value);
                        retorno = produtos.porId(id);
                    } catch (Exception e) {
                        System.out.println("com.najasoftware.converter.ProdutoConverter.getAsObject( Erro ao pegar o objeto ) ");
                    }
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Produto produto = (Produto) value;
			return produto.getId() == null ? null : produto.getId().toString();
		}
		
		return "";
	}

}