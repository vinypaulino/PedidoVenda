package com.naja.controller;

import com.naja.enumerator.AnotacaoTipo;
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
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Named
@ViewScoped
public class CadAtendimentoController extends Controller implements Serializable {

    @Inject
    private Usuario usuario;

    private List<Usuario> atendenteList;

    @Inject
    private UsuarioRN usuarioRN;

    @Inject
    private Pessoa pessoa;

    @Inject
    private PessoaRN pessoaRN;

    @Inject
    private Atendimento atendimento;

    @Inject
    private AtendimentoRN atendimentoRN;

    @Inject
    private Anotacao anotacao;

    @Inject
    private Pessoa cliente;

    @Inject
    private Usuario atendente;

    @Inject
    private Atendimentos atendimentos;

    private Map mapCriteriosBusca = new HashMap();
    private String id;
    private LazyDataModel<Atendimento> model;
    private AtendimentoStatus status;
    private List<Usuario> tendenteList;
    private List<Pessoa> clienteList;
    private List<Atendimento> atendimentoList;
    private Date dtInicioAtendimento;
    private Date dtFimAtendimento;
    private Date dtMax;
    private AtendimentoStatus[] atendimentoStatus;
    private int i, totalAtendimento;

    @PostConstruct
    public void init() {

        //Setando a primeira aba como ativa        
        this.i = 0;
       
        atendimento = new Atendimento();
        anotacao = new Anotacao();

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
                mapCriteriosBusca.put("id", id);
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

                List<Atendimento> temporario = atendimentos.buscaPorPaginacao(mapCriteriosBusca, filtros);

                totalAtendimento = temporario.size();
 
                return temporario;
            }

        };

    }

    public List<Anotacao> anotacoesPorId(Atendimento atendimento) {
        if (atendimento != null) {
            Atendimento atendimentoPorId = atendimentoRN.porId(atendimento.getId());
            List<Anotacao> anotacoes = atendimentoPorId.getAnotacoes();
            return anotacoes;
        }
        return null;
    }

    public void salvar() {

        Date dtAnotacao = new Date(System.currentTimeMillis());
        Time horaAnotacao = new Time(System.currentTimeMillis());

        //Pega o usuario Logado da insert como sendo o telefoneista
        atendimento.setTelefonista(contextoBean.getUsuario());
        atendimento.setDataAtendimento(dtAnotacao);
        atendimento.setHoraAtendimento(horaAnotacao);
        atendimento.setStatus(AtendimentoStatus.AG_ATENDIMENTO);

        if (anotacao.getDescricao().trim().length() > 0) {
            List<Anotacao> anotacoes = new ArrayList<>();
            anotacao.setDataAnotacao(dtAnotacao);
            anotacao.setHoraAnotacao(horaAnotacao);
            anotacao.setAtendente(contextoBean.getUsuario());
            anotacao.setAtendimento(atendimento);
            anotacao.setAnotacaoTipo(AnotacaoTipo.AGENDA);
            anotacao.setFalouCom(atendimento.getFalarCom());
            anotacoes.add(anotacao);
            atendimento.setAnotacoes(anotacoes);
        }

        atendimentoRN.salvar(atendimento);

        init();
        info("Atendimento Cadastrado com sucesso!");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getAtendenteList() {
        if (atendenteList == null) {
            atendenteList = usuarioRN.todosAtendente();
        }
        return atendenteList;
    }

    public void setAtendenteList(List<Usuario> atendenteList) {
        this.atendenteList = atendenteList;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    public void setClienteList(List<Pessoa> clienteList) {
        this.clienteList = clienteList;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public List<Usuario> getTendenteList() {
        return tendenteList;
    }

    public void setTendenteList(List<Usuario> tendenteList) {
        this.tendenteList = tendenteList;
    }

    public Anotacao getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(Anotacao anotacao) {
        this.anotacao = anotacao;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
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

    public void setDtMax(Date dtMax) {
        this.dtMax = dtMax;
    }

    public AtendimentoStatus[] getAtendimentoStatus() {
        return atendimentoStatus;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Usuario getAtendente() {
        return atendente;
    }

    public void setAtendente(Usuario atendente) {
        this.atendente = atendente;
    }

    public AtendimentoStatus getStatus() {
        return status;
    }

    public void setStatus(AtendimentoStatus status) {
        this.status = status;
    }

    public int getTotalAtendimento() {       
        return totalAtendimento;
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

    public List<Atendimento> getAtendimentoList() {
        return atendimentoList;
    }

    public void setAtendimentoList(List<Atendimento> atendimentoList) {
        this.atendimentoList = atendimentoList;
    }

}
