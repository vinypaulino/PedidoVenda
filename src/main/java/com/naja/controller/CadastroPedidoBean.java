/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.enumerator.FormaPagamento;
import com.naja.model.EnderecoEntrega;
import com.naja.model.Grupo;
import com.naja.model.ItemPedido;
import com.naja.model.Pedido;
import com.naja.model.Produto;
import com.naja.model.Usuario;
import com.naja.reposity.Produtos;
import com.naja.reposity.Usuarios;
import com.naja.rn.PedidoRN;
import com.naja.util.Controller;
import com.naja.util.DAOException;
import com.naja.util.jsf.FacesUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author VinyPaulino
 */
@Named
@ViewScoped
public class CadastroPedidoBean extends Controller implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    @Inject
    Produtos produtos;

    @Inject
    private PedidoRN pedidoRN;

    private String codigoProduto;

    @Produces
    @PedidoEdicao
    private Pedido pedido;

    private List<Usuario> clientes;

    private List<Produto> todosProdutos;

    private Produto produtoLinhaEditavel = new Produto();

    private int quantidadeItem = 1;

    private String itemDescricao;

    // variavel utilizada para fazer a pesquisa do produto 
    private String nomeProduto;

    public CadastroPedidoBean() {

    }

    @PostConstruct
    public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            if (pedido == null) {
                limpar();
            }
            this.clientes = this.usuarios.clientes();
            this.todosProdutos = this.produtos.produtos();
//            this.pedido.adicionarItemVazio();
        }
    }
    
    
    public void pesquisar(){
        this.todosProdutos = this.produtos.porNomeSemelhante(nomeProduto);        
    }

    public void onRowSelect(SelectEvent event) {
        produtoLinhaEditavel = ((Produto) event.getObject());
    }

    public void onRowSelect1(SelectEvent event) {
        System.out.println("Teste de Swipe");
//produtoLinhaEditavel = ((Produto) event.getObject());        
    }

    //método para adicionar o Item escolhido pelo usuario a Lista de Itens 
    public String adicionaItem() {

        if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
            info("Item já Lancado ! ");
            return "pm:lancProduto";
        } else {
            ItemPedido item = new ItemPedido();
            item.setProduto(produtoLinhaEditavel);
            item.setQuantidade(quantidadeItem);
            item.setItemDescricao(itemDescricao);
            item.setPedido(this.pedido);
            BigDecimal total = (produtoLinhaEditavel.getValorUnitario().multiply(new BigDecimal(item.getQuantidade())));
            item.setValorTotal(total);

            this.pedido.getItens().add(item);
            this.pedido.recalcularValorTotal();
            System.out.println("Valor total é " + this.pedido.getValorTotal());

            quantidadeItem = 1;
            itemDescricao = "";
            return "pm:itens";
        }

    }

    public void enviarEntrega() {
        this.pedido = pedidoRN.enviarEntrega(this.pedido);
        info("Status atualizado para Entrega com sucesso! ");
    }

    public void finalizarPedido() {
        this.pedido = pedidoRN.finalizarPedido(this.pedido);
        info("Pedido finalizado com sucesso! ");
    }

    public void adicionarLinha() {
        this.pedido.adicionarItemVazio();
    }

    private void limpar() {
        pedido = new Pedido();
        pedido.setEnderecoEntrega(new EnderecoEntrega());
    }

    //capturar o evento disparado ao emitir um novo pedido pela classe EmissaoPedidoBean
    public void pedidoAlterado(@Observes PedidoAlteradoEvent event) {
        this.pedido = event.getPedido();
    }

    public void salvar() throws DAOException {
        this.pedido.removerItemVazio();
        try {
            this.pedido = this.pedidoRN.salvar(this.pedido);
        } finally {
            this.pedido.adicionarItemVazio();
        }

        info("Pedido salvo com sucesso!");
    }

    public void carregarProdutoPorCodigo() {
        if (StringUtils.isNotEmpty(this.codigoProduto)) {
            this.produtoLinhaEditavel = this.produtos.porCodigoProduto(this.codigoProduto);
            this.carregarProdutoLinhaEditavel();
        }
    }

    public void adicionarItemDescricao(ItemPedido item) {
        item.setItemDescricao(item.getItemDescricao());

    }

    public void carregarProdutoLinhaEditavel() {
        ItemPedido item = this.pedido.getItens().get(0);

        if (this.produtoLinhaEditavel != null) {

            if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
                FacesUtil.error("Já existe um item no pedido com o produto informado");
            } else {
                item.setProduto(this.produtoLinhaEditavel);
                item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());

                this.pedido.adicionarItemVazio();
                this.produtoLinhaEditavel = null;
                this.codigoProduto = null;

                this.pedido.recalcularValorTotal();
            }
        }
    }

    public List<Produto> completarProduto(String nome) {
        return this.produtos.porNome(nome);
    }

    public void atualizarQuantidade(ItemPedido item, int linha) {
        if (item.getQuantidade() < 1) {
            if (linha == 0) {
                item.setQuantidade(1);
            } else {
                this.getPedido().getItens().remove(linha);
            }
        }
        this.pedido.recalcularValorTotal();
    }

    public FormaPagamento[] getFormasPagamento() {
        return FormaPagamento.values();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Usuario> getClientes() {
        return clientes;
    }

    public void setClientes(List<Usuario> clientes) {
        this.clientes = clientes;
    }

    public Produto getProdutoLinhaEditavel() {
        return produtoLinhaEditavel;
    }

    public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
        this.produtoLinhaEditavel = produtoLinhaEditavel;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    private boolean existeItemComProduto(Produto produto) {
        boolean existeItem = false;

        for (ItemPedido item : this.getPedido().getItens()) {
            if (produto.equals(item.getProduto())) {
                existeItem = true;
                break;
            }
        }
        return existeItem;
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

    public List<Produto> getTodosProdutos() {
        return todosProdutos;
    }

    public void setTodosProdutos(List<Produto> todosProdutos) {
        this.todosProdutos = todosProdutos;
    }

    public int getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(int quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

    public String getItemDescricao() {
        return itemDescricao;
    }

    public void setItemDescricao(String itemDescricao) {
        this.itemDescricao = itemDescricao;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

}
