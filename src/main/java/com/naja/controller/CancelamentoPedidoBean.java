/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.model.Pedido;
import com.naja.rn.CancelamentoPedidoRN;
import com.naja.util.Controller;
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
public class CancelamentoPedidoBean extends Controller implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private CancelamentoPedidoRN cancelamentoPedidoRN;
    
    @Inject
    private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
    
    @Inject
    @PedidoEdicao
    private Pedido pedido;
    
    public void cancelarPedido(){
        this.pedido = this.cancelamentoPedidoRN.cancelar(this.pedido);
        this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));
        
        info("Pedido Cancelado com sucesso! ");
    }
    
    
}
