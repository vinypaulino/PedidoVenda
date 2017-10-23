package com.naja.controller;

import com.naja.enumerator.AtendimentoStatus;
import com.naja.model.Anotacao;
import com.naja.model.Atendimento;
import com.naja.model.Pessoa;
import com.naja.model.Usuario;
import com.naja.reposity.Atendimentos;
import com.naja.rn.AtendimentoRN;
import com.naja.rn.PessoaRN;
import com.naja.rn.UsuarioRN;
import com.naja.util.Controller;
import com.naja.util.RNException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Named
@ViewScoped
public class AdmAtendimentoController extends Controller implements Serializable {

    private List<Usuario> atendenteList;
    private AtendimentoStatus[] atendimentoStatus;

    @Inject
    private UsuarioRN usuarioRN;

    @Inject
    private PessoaRN pessoaRN;

    @Inject
    private AtendimentoRN atendimentoRN;

    @Inject
    private Usuario atendente;

    @Inject
    private Atendimento atendimento;

    @Inject
    private Atendimentos atendimentos;

    @Inject
    private Pessoa cliente;

    private LazyDataModel<Atendimento> model;
    private AtendimentoStatus status;
    private Map mapCriteriosBusca = new HashMap();
    private List<Pessoa> clienteList;
    private List<Atendimento> atendimentoList;
    private Set<Atendimento> atendimentoListEditado;
    private Date dtInicioAtendimento;
    private Date dtFimAtendimento;
    private Date dtMax;
    private int i, totalAtendimento;
    private boolean desabilitado;
    private String id;

    /*
    Inicializa as variaveis.
     */
    @PostConstruct
    public void init() {

        //Setando a primeira aba como ativa
        this.i = 0;

        //Desabilitando a aba editar
        this.desabilitado = true;

        atendimento = new Atendimento();
        if (atendimentoStatus == null) {
            atendimentoStatus = AtendimentoStatus.values();
        }

        if (dtInicioAtendimento == null || dtFimAtendimento == null || dtMax == null) {
            dtInicioAtendimento = new Date();
            dtFimAtendimento = new Date();
            dtMax = new Date();
        }

        model = new LazyDataModel<Atendimento>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<Atendimento> load(int posicaoPrimeiraLinha, int maximoPorPagina, String ordernarPeloCampo, SortOrder ordernarAscOuDesc, Map<String, Object> filtros) {

                String ordenacao = ordernarAscOuDesc.toString();

                if (SortOrder.UNSORTED.equals(ordernarAscOuDesc)) {
                    ordenacao = SortOrder.ASCENDING.toString();
                }

                mapCriteriosBusca.put("posicaoPrimeiraLinha", posicaoPrimeiraLinha);
                mapCriteriosBusca.put("maximoPorPagina", maximoPorPagina);
                mapCriteriosBusca.put("ordernarPeloCampo", ordernarPeloCampo);
                mapCriteriosBusca.put("ordenacao", ordenacao);
                mapCriteriosBusca.put("dtInicio", dtInicioAtendimento);
                mapCriteriosBusca.put("dtFim", dtFimAtendimento);
                mapCriteriosBusca.put("id",id);
                mapCriteriosBusca.put("status", status);

                if (cliente != null) {
                    if (cliente.getId() != null) {
                        mapCriteriosBusca.put("cliente", cliente);
                    }
                }

                if (atendente != null) {
                    if (atendente.getId() != null) {
                        mapCriteriosBusca.put("atendente", atendente);
                    }
                }

                if (getRowCount() <= 0 || (filtros != null && !filtros.isEmpty())) {
                    setRowCount(atendimentos.countAll(filtros));
                }

                // quantidade a ser exibida em cada página 
                setPageSize(maximoPorPagina);
                
                List<Atendimento> temp = atendimentos.buscaPorPaginacao(mapCriteriosBusca, filtros);

                totalAtendimento = temp.size();
                
                return temp;
            }

        };
    }

    public List<Anotacao> anotacoesPorId(Atendimento atendimento) {
        Atendimento atendimentoPorId = atendimentoRN.porId(atendimento.getId());
        List<Anotacao> anotacoes = atendimentoPorId.getAnotacoes();
        return anotacoes;
    }

    /*
    Deleta o atendimento selecionado se este não tiver 
    Lançamentos de anotações
     */
    public void deletar() {
        atendimentoRN.deletar(atendimento);
        atendimentoList = atendimentoRN.localizar(mapCriteriosBusca);
    }

    /*
    Apenas troca a aba do usuario
     */
    public void editar() {
        setDesabilitado(false);
        this.i = 1;
    }

    /*
    Salva os dados editados e ja limpa os objetos init();
     */    
    public void salvar() {
        atendimentoListEditado = new HashSet<>();

        //Se status for null devemos gravar como sendo o status novo
        if (atendimento.getStatus() == null) {
            atendimento.setStatus(AtendimentoStatus.AG_ATENDIMENTO);
        }

        atendimentoRN.salvar(atendimento);
        atendimento = atendimentoRN.porId(atendimento.getId());
        atendimentoListEditado.add(atendimento);
        atendimentoList = atendimentoRN.localizar(mapCriteriosBusca);
        info("Atualizado com sucesso!");
        init();
    }

    public List<Usuario> getAtendenteList() {
        if (atendenteList == null) {
            atendenteList = usuarioRN.todosAtendente();
        }
        return atendenteList;
    }

    public List<Pessoa> getClienteList() {
        if (clienteList == null) {
            try {
                clienteList = pessoaRN.todas();
            } catch (RNException ex) {
                Logger.getLogger(CadAtendimentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return clienteList;
    }

    public List<Atendimento> getAtendimentoList() {
        return atendimentoList;
    }

    public AtendimentoStatus[] getAtendimentoStatus() {
        return atendimentoStatus;
    }

    public Date getDtInicioAtendimento() {
        return dtInicioAtendimento;
    }

    public void setDtInicioAtendimento(Date dtInicioAtendimento) {
        this.dtInicioAtendimento = dtInicioAtendimento;
    }

    public Date getDtFimAtendimento() {
        return dtFimAtendimento;
    }

    public void setDtFimAtendimento(Date dtFimAtendimento) {
        this.dtFimAtendimento = dtFimAtendimento;
    }

    public Date getDtMax() {
        return dtMax;
    }

    public void setAtendente(Usuario atendente) {
        this.atendente = atendente;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public Usuario getAtendente() {
        return atendente;
    }

    public AtendimentoStatus getStatus() {
        return status;
    }

    public void setStatus(AtendimentoStatus status) {
        this.status = status;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Set<Atendimento> getAtendimentoListEditado() {
        return atendimentoListEditado;
    }

    public void setAtendimentoListEditado(Set<Atendimento> atendimentoListEditado) {
        this.atendimentoListEditado = atendimentoListEditado;
    }

    public int getTotalAtendimento() {
        return totalAtendimento;
    }

    public boolean isDesabilitado() {
        return desabilitado;
    }

    public void setDesabilitado(boolean desabilitado) {
        this.desabilitado = desabilitado;
    }

    public LazyDataModel<Atendimento> getModel() {
        return model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
