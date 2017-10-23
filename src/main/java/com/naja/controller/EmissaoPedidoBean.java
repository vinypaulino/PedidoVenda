/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.model.Pedido;
import com.naja.rn.EmissaoPedidoRN;
import com.naja.util.Controller;
import com.naja.util.DAOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vinyPaulino
 */
@Named
@RequestScoped
public class EmissaoPedidoBean extends Controller implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EmissaoPedidoRN emissaoPedidoRN;
    
    @Inject
    @PedidoEdicao
    private Pedido pedido;
    
    @Inject
    private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
    
    public void emitirPedido() throws DAOException{
        this.pedido.removerItemVazio();
    
        
        try {
            this.pedido = this.emissaoPedidoRN.emitir(this.pedido);
            // lancar um evento CDI
            this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));
            
            info("Pedido Enviado com Sucesso");
        } finally {
            this.pedido.adicionarItemVazio();
        }
    }
    
}
