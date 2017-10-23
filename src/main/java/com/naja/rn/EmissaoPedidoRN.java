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
import com.naja.util.DAOException;
import com.naja.util.RegraNegocio;
import com.naja.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author vinyPaulino
 */
public class EmissaoPedidoRN extends RegraNegocio implements Serializable  {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private PedidoRN pedidoRN;
    
    @Inject
    private Pedidos pedidos;
    
    @Transactional
    public Pedido emitir(Pedido pedido) throws DAOException{
              
        if (pedido.getId() == null){
            throw new NegocioException("Salve o pedido para poder envia-lo ");
        }
        
        if (pedido.isNaoEmissivel()){
            throw new NegocioException("Pedido não pode ser emitido com o status: "
            + pedido.getStatus().getDescricao() + ".");
        }
 
        pedido.setStatus(StatusPedido.EMITIDO);   
        
        pedido = pedidos.guardar(pedido);
                
        return pedido;        
    }            
}
