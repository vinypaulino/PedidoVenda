/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.enumerator.StatusPedido;
import com.naja.filter.PedidoFilter;
import com.naja.model.Grupo;
import com.naja.model.Pedido;
import com.naja.model.Usuario;
import com.naja.reposity.Pedidos;
import com.naja.util.Controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Naja
 */
@Named
@ViewScoped
public class PesquisaPedidosBean extends Controller implements Serializable {

    private static final long serialVersionUID = 1L;

    public PesquisaPedidosBean() {

    }

    @Inject
    private Pedidos pedidos;

    @Inject
    private PedidoFilter filtro;

    private List<Pedido> pedidosFiltrados = new ArrayList<>();

    public PesquisaPedidosBean(PedidoFilter filtro) {
        this.filtro = new PedidoFilter();

    }

    public void inicializar() {
        if (isAdministrador()) {
            pedidosFiltrados = pedidos.filtradosAdm();
        } else {
            Usuario cliente = contextoBean.getUsuario();
            pedidosFiltrados = pedidos.filtradosCliente(cliente);
        }
    }

    public void pesquisar() {
        Usuario cliente = contextoBean.getUsuario();
        pedidosFiltrados = pedidos.filtrados(filtro, isAdministrador(), cliente);
    }

    public StatusPedido[] getStatuses() {
        return StatusPedido.values();
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public PedidoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(PedidoFilter filtro) {
        this.filtro = filtro;
    }

    public List<Pedido> getPedidosFiltrados() {
        return pedidosFiltrados;
    }

    public void setPedidosFiltrados(List<Pedido> pedidosFiltrados) {
        this.pedidosFiltrados = pedidosFiltrados;
    }

    public boolean isAdministrador() {
        Usuario usuario = contextoBean.getUsuario();

        List<Grupo> grupos = usuario.getGrupos();

        for (Grupo gp : grupos) {
            if (gp.getDescricao().equals("Administradores")) {
                return true;
            }
        }
        return false;
    }
}
