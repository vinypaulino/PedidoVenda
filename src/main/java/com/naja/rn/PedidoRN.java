/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.enumerator.StatusPedido;
import com.naja.model.Pedido;
import com.naja.model.Usuario;
import com.naja.reposity.Pedidos;
import com.naja.reposity.Usuarios;
import com.naja.service.NegocioException;
import com.naja.util.DAOException;
import com.naja.util.RegraNegocio;
import com.naja.util.jpa.Transactional;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author Naja
 */
public class PedidoRN extends RegraNegocio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Pedidos pedidos;

    @Inject
    private Usuarios usuarios;

    @Inject
    private Usuario cliente;

    @Transactional
    public Pedido salvar(Pedido pedido) throws DAOException {

        if (pedido.getId() == null) {
            pedido.setDataCriacao(new Date());
            pedido.setStatus(StatusPedido.ORCAMENTO);

            cliente = contextoBean.getUsuario();
            cliente = usuarios.porId(cliente.getId());
            pedido.setCliente(cliente);
        }
        pedido.recalcularValorTotal();

        if (pedido.isNaoAlteravel()) {
            throw new NegocioException("Pedido não pode ser alterado no status: "
                    + pedido.getStatus().getDescricao());
        }

        if (pedido.getItens().isEmpty()) {
            throw new NegocioException("O pedido deve possuir pelo menos um item.");
        }
        if (pedido.getFormaPagamento() == null) {
            throw new NegocioException("Selecione a forma de Pagamento");
        }

        if (pedido.isValorTotalNegativo()) {
            throw new NegocioException("Valor Total do pedido nao pode ser negativo");
        }

        pedido = this.pedidos.guardar(pedido);

        return pedido;
    }

    @Transactional
    public Pedido enviarEntrega(Pedido pedido) {
        if (!pedido.getStatus().equals(StatusPedido.EMITIDO)) {
            throw new NegocioException("Para enviar um pedido para Entrega é necessario estar no status EMITIDO");
        }
        pedido.setStatus(StatusPedido.ENTREGA);
        this.pedidos.guardar(pedido);
        return pedido;
    }

    @Transactional
    public Pedido finalizarPedido(Pedido pedido) {
        if (!pedido.getStatus().equals(StatusPedido.ENTREGA)) {
            throw new NegocioException("Para enviar um pedido para Entrega é necessario estar no status Entrega");
        }
        pedido.setStatus(StatusPedido.FINALIZADO);
        pedido = this.pedidos.guardar(pedido);
        return pedido;
    }
}
