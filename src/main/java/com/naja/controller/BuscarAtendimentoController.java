package com.naja.controller;

import com.naja.enumerator.AtendimentoStatus;
import com.naja.model.Anexo;
import com.naja.model.Anotacao;
import com.naja.model.Atendimento;
import com.naja.model.Pessoa;
import com.naja.model.Usuario;
import com.naja.reposity.Atendimentos;
import com.naja.rn.AnotacaoRN;
import com.naja.rn.AtendimentoRN;
import com.naja.rn.EventoRN;
import com.naja.rn.PessoaRN;
import com.naja.rn.UsuarioRN;
import com.naja.util.Controller;
import com.naja.util.RNException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;

/**
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Named
@ViewScoped
public class BuscarAtendimentoController extends Controller implements Serializable {

    @Inject
    private Atendimento selectedAtendimento;

    @Inject
    private UsuarioRN usuarioRN;

    @Inject
    private PessoaRN pessoaRN;

    @Inject
    private AtendimentoRN atendimentoRN;

    @Inject
    private Usuario atendente;

    @Inject
    private Atendimentos atendimentos;

    @Inject
    private Pessoa cliente;

    @Inject
    private AnotacaoRN anotacaoRN;

    @Inject
    private EventoRN eventoRN;

    @Inject
    private Anexo anexoDownload;

    private StreamedContent downloadFile;
    private LazyDataModel<Atendimento> model;
    private AtendimentoStatus status;
    private Map mapCriteriosBusca = new HashMap();
    private List<Pessoa> clienteList;
    private List<Atendimento> atendimentoList;
    private Date dtInicioAtendimento;
    private Date dtFimAtendimento;
    private Date dtInicioAnotacao;
    private Date dtFimAnotacao;
    private Date dtMax;
    private int i, totalAtendimento;
    private boolean desabilitado;
    private String id;
    private File f;
    private List<Usuario> atendenteList;
    private List<Anotacao> anotacoesAgendaList;

    private AtendimentoStatus[] atendimentoStatus;

    /*
    Inicializa as variaveis.
     */
    @PostConstruct
    public void init() {

        //Setando a primeira aba como ativa
        this.i = 0;

        //Desabilitando a aba editar
        this.desabilitado = true;

        if (atendimentoStatus == null) {
            atendimentoStatus = AtendimentoStatus.values();
        }

        if (dtInicioAtendimento == null || dtFimAtendimento == null || dtMax == null) {
            dtInicioAtendimento = new Date();
            dtFimAtendimento = new Date();
            dtMax = new Date();
        }

        model = new LazyDataModel<Atendimento>() {

            private List<Atendimento> temp;

            private static final long serialVersionUID = 1L;

            @Override
            public Atendimento getRowData(String rowKey) {
                for (Atendimento atend : temp) {
                    if (atend.getId().equals(Long.parseLong(rowKey))) {
                        return atend;
                    }
                }

                return null;
            }

            @Override
            public Object getRowKey(Atendimento atend) {
                return atend.getId();
            }

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
                mapCriteriosBusca.put("dtInicioAnotacao", dtInicioAnotacao);
                mapCriteriosBusca.put("dtFimAnotacao", dtFimAnotacao);
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

                temp = atendimentos.buscaPorPaginacao(mapCriteriosBusca, filtros);

                totalAtendimento = temp.size();

                return temp;
            }

        };
    }

    public void downloadFile(String nomeArquivo) throws IOException {

        String path = "c://files//";

        String nomeArqNew = nomeArquivo;

        //NOME QUE VAI APARECER NO BROWSER
        String filename = nomeArqNew;

        //ARQUIVO QUE SERÁ BAIXADO
        File file = new File(path, nomeArqNew);

        if (file.exists()) {
            
             OutputStream out = null;

            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

            out = response.getOutputStream();

            //response.setContentType("text/xml");
            response.addHeader("Content-Disposition", "attachment; filename=" + filename);
            response.setContentLength((int) file.length());

            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream responseOutputStream = response.getOutputStream();
            int bytes;
            while ((bytes = fileInputStream.read()) != -1) {
                responseOutputStream.write(bytes);
            }

            fc.responseComplete();
            responseOutputStream.flush();
            responseOutputStream.close();
            fileInputStream.close();

        } else {
            error("Erro no download");
        }

    }

    public void setDownloadFile(StreamedContent downloadFile) {
        this.downloadFile = downloadFile;
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

    public void onRowSelect(SelectEvent event) {
        selectedAtendimento = atendimentoRN.porId(selectedAtendimento.getId());
        anotacoesAgendaList = anotacaoRN.todasAnotacaoAtendimento(selectedAtendimento);
        setDesabilitado(false);
        this.i = 1;
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

    public Atendimento getSelectedAtendimento() {
        return selectedAtendimento;
    }

    public void setSelectedAtendimento(Atendimento selectedAtendimento) {
        this.selectedAtendimento = selectedAtendimento;
    }

    public List<Anotacao> getAnotacoesAgendaList() {
        return anotacoesAgendaList;
    }

    public void setAnotacoesAgendaList(List<Anotacao> anotacoesAgendaList) {
        this.anotacoesAgendaList = anotacoesAgendaList;
    }

    public Anexo getAnexoDownload() {
        return anexoDownload;
    }

    public void setAnexoDownload(Anexo anexoDownload) {
        this.anexoDownload = anexoDownload;
    }

    public Date getDtInicioAnotacao() {
        return dtInicioAnotacao;
    }

    public void setDtInicioAnotacao(Date dtInicioAnotacao) {
        this.dtInicioAnotacao = dtInicioAnotacao;
    }

    public Date getDtFimAnotacao() {
        return dtFimAnotacao;
    }

    public void setDtFimAnotacao(Date dtFimAnotacao) {
        this.dtFimAnotacao = dtFimAnotacao;
    }
    
}
