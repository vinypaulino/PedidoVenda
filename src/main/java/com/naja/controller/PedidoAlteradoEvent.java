/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.model.Pedido;

/**
 *
 * @author Naja
 */
public class PedidoAlteradoEvent {
        
        private Pedido pedido;
        
        public PedidoAlteradoEvent(Pedido pedido){
            this.pedido = pedido;        
        }
        
        public Pedido getPedido(){
            return pedido;
        }


}
