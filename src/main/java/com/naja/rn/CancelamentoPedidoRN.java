/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.enumerator.StatusPedido;
import com.naja.model.Pedido;
import com.naja.reposity.Pedidos;
import com.naja.service.NegocioException;
import com.naja.util.RegraNegocio;
import com.naja.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Naja
 */
public class CancelamentoPedidoRN extends RegraNegocio implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Inject
    private Pedidos pedidos;
    
    @Transactional
    public Pedido cancelar(Pedido pedido) {
        pedido = this.pedidos.porId(pedido.getId());
        
        if (pedido.getId() == null){
             throw new NegocioException("Não é possivel cancelar um pedido ainda não salvo");        
        }
        
        if (pedido.getStatus().getDescricao().equals("CANCELADO")){
            throw new NegocioException("Pedido já Cancelado");
        }
        
        pedido.setStatus(StatusPedido.CANCELADO);   
        
        pedidos.guardar(pedido);
        return pedido;
    }
    
}
