package com.naja.controller;

import com.naja.enumerator.AnotacaoTipo;
import com.naja.enumerator.AtendimentoStatus;
import com.naja.model.Anexo;
import com.naja.model.Anotacao;
import com.naja.model.Atendimento;
import com.naja.model.Evento;
import com.naja.model.Usuario;
import com.naja.rn.AnotacaoRN;
import com.naja.rn.AtendimentoRN;
import com.naja.rn.EventoRN;
import com.naja.rn.UsuarioRN;
import com.naja.util.Controller;
import com.naja.util.FileUtils;
import com.naja.util.jpa.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Named
@ViewScoped
public class AtendimentoSuporteController extends Controller implements Serializable {

    @Inject
    private AtendimentoRN atendimentoRN;

    @Inject
    private Atendimento atendimento;

    @Inject
    private AnotacaoRN anotacaoRN;

    @Inject
    private EventoRN eventoRN;

    @Inject
    private Anotacao relatoCliente;

    @Inject
    private Anotacao providencias;

    @Inject
    private Anotacao anotacaoSuporte;

    @Inject
    private Anexo anexo;

    @Inject
    private FileUtils fileUtils;

    @Inject
    private UsuarioRN usuarioRN;

    private UploadedFile file;
    private StreamedContent downloadFile;
    private int i;
    private boolean desabilitado;
    private List<Atendimento> myAtendimentoList;
    private List<Atendimento> oursAtendimentoList;
    private List<Atendimento> theirAtendimentoList;
    private List<Evento> eventoList;
    private List<Evento> eventoSelectedList;
    private List<Anexo> anexosList;
    private List<Anotacao> anotacoesAgendaList;
    private Date dtInicioAtendimento;
    private Date dtFimAtendimento;
    private String falouCom;
    private boolean paraAnalise;
    private File f;
    private String nomeArqNew;

    /*
    Inicializa as variaveis.
     */
    @PostConstruct
    public void init() {

        //I N I C I A L I Z A N D O  V A R I A V E I S 
        i = 0;
        desabilitado = true;
        //Inicializando a lista de arquivos
        anexosList = new ArrayList<>();
        atendimento = new Atendimento();
        relatoCliente = new Anotacao();
        providencias = new Anotacao();
        anotacaoSuporte = new Anotacao();
        paraAnalise = false;
        falouCom = "";
        eventoSelectedList = new ArrayList<>();
        anexosList = new ArrayList<>();

        //############### GRID 1 Panel Grid com Meus Atendimentos tanto no STATUS novo como atendento        
        Map myCriteriosBusca = new HashMap();

        myCriteriosBusca.put("atendente.id", contextoBean.getUsuario().getId());
        myCriteriosBusca.put("tipo", "my");
        Set<AtendimentoStatus> status = new HashSet();
        status.add(AtendimentoStatus.AG_ATENDIMENTO);
        status.add(AtendimentoStatus.ATENDENDO);
        myCriteriosBusca.put("status", status);
        myAtendimentoList = atendimentoRN.localizarSuporte(myCriteriosBusca);

        //############### GRID 2 Preenchendo a segunda PANEL GRID da tela, com atendimento sem destinatario
        Map oursCriteriosBusca = new HashMap();

        //Trazer apenas atendimentos sem atendentes        
        oursCriteriosBusca.put("tipo", "ours");

        //Preenchendo criterios do status
        Set<AtendimentoStatus> oursStatus = new HashSet();
        oursStatus.add(AtendimentoStatus.AG_ATENDIMENTO);
        oursCriteriosBusca.put("status", oursStatus);

        //Realizando a busca no banco com os criterios
        oursAtendimentoList = atendimentoRN.localizarSuporte(oursCriteriosBusca);

        ////############### GRID 3 Preenchendo a terceira Panel Grid com os atendimentos dos outros colegas;
        Map theirCriteriosBusca = new HashMap();

        //Trazer apenas atendimentos sem atendentes
        theirCriteriosBusca.put("tipo", "their");
        theirCriteriosBusca.put("atendente.id", contextoBean.getUsuario().getId());

        //Preenchendo criterios do status
        Set<AtendimentoStatus> theirStatus = new HashSet();
        theirStatus.add(AtendimentoStatus.AG_ATENDIMENTO);
        theirStatus.add(AtendimentoStatus.ATENDENDO);
        theirCriteriosBusca.put("status", theirStatus);

        //Realizando a busca no banco com os criterios
        theirAtendimentoList = atendimentoRN.localizarSuporte(theirCriteriosBusca);

    }
    
    public void selecionarAtendimento() {
        //Pegando o ID que foi enviado via JAVASCRIP
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String idDoAtendimentoSelecionado = (String) map.get("id");

        if (StringUtils.isNotBlank(idDoAtendimentoSelecionado)) {
            atendimento = atendimentoRN.selecionouAtendimento(Long.parseLong(idDoAtendimentoSelecionado));
            anotacoesAgendaList = anotacaoRN.todos(atendimento, AnotacaoTipo.AGENDA);
            this.i = 1;
            desabilitado = false;
        }

    }
    
    public void salvar() {

        String newDiretorio = "c://files//";

        if (!eventoSelectedList.isEmpty()) {

            Date dtAnotacao = new Date(System.currentTimeMillis());
            Time horaAnotacao = new Time(System.currentTimeMillis());
            List<Anotacao> anot = new ArrayList<>();

            atendimento.setAtendente(contextoBean.getUsuario());

            if (relatoCliente.getDescricao().trim().length() > 0) {
                relatoCliente.setAnotacaoTipo(AnotacaoTipo.CLIENTE_RELATO);
                relatoCliente.setDataAnotacao(dtAnotacao);
                relatoCliente.setHoraAnotacao(horaAnotacao);
                relatoCliente.setAtendimento(atendimento);
                relatoCliente.setAtendente(atendimento.getAtendente());
                relatoCliente.setFalouCom(falouCom);
                anot.add(relatoCliente);
            }

            if (providencias.getDescricao().trim().length() > 0) {
                providencias.setAnotacaoTipo(AnotacaoTipo.SUPORTE);
                providencias.setDataAnotacao(dtAnotacao);
                providencias.setHoraAnotacao(horaAnotacao);
                providencias.setAtendimento(atendimento);
                providencias.setAtendente(atendimento.getAtendente());
                providencias.setFalouCom(falouCom);
                anot.add(providencias);
            }

            if (anotacaoSuporte.getDescricao().trim().length() > 0) {
                anotacaoSuporte.setAnotacaoTipo(AnotacaoTipo.SUPORTE);
                anotacaoSuporte.setDataAnotacao(dtAnotacao);
                anotacaoSuporte.setHoraAnotacao(horaAnotacao);
                anotacaoSuporte.setAtendimento(atendimento);
                anotacaoSuporte.setAtendente(atendimento.getAtendente());
                anotacaoSuporte.setFalouCom(falouCom);
                anot.add(anotacaoSuporte);
            }

            if (isParaAnalise()) {
                atendimento.setStatus(AtendimentoStatus.AG_ANALISE);
            } else {
                atendimento.setStatus(AtendimentoStatus.ATENDIDO);
            }

            //Adicionando os eventos ao atendimento
            atendimento.setEventos(eventoSelectedList);

            //Adicionando os anexos ao atendimento
            for (Anexo anexo : anexosList) {
                fileUtils.moveArquivo(anexo.getPath() + anexo.getNomeArquivo(), newDiretorio);
                anexo.setPath(newDiretorio);
            }

            atendimento.setAnexos(anexosList);

            //Adicionando as anotacoes ao atendimento
            atendimento.setAnotacoes(anot);

            //Salvando
            atendimentoRN.salvarAtendimentoSuporte(atendimento);
            info("Salvo com sucesso");
            init();
        } else {
            error("É obrigatório escolher um evento");
        }
    }

    public void deletarAnexo() {
        File f = new File(anexo.getPath(), anexo.getNomeArquivo());
        f.delete();
        f = null;
        anexosList.remove(anexo);
        info("Arquivo Apagado");
    }

    public void handleFileUpload(FileUploadEvent event) {

        //Novo nome do arquivo baseado na data
        String nomeArqOld, path;

        UploadedFile uf = event.getFile();

        //Pegando o nome do arquivo enviado pelo cliente
        nomeArqOld = uf.getFileName();

        //Pegando apenas a extensao do arquivo
        String extensaoArquivo = nomeArqOld.substring(nomeArqOld.indexOf(".") + 1);

        //Gerando dados para o nome do arquivo. Ano/Mes/Dia/Hora/Minuto/Segundo/Sentésimos
        Date dtAtual = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        nomeArqNew = formater.format(dtAtual) + "." + extensaoArquivo;

        // aqui verifico se é linux ou windows
        if (System.getProperties().get("os.name").toString().trim().equalsIgnoreCase("Linux")) {
            path = "/home/workspace/age/files/";
        } else {
            path = "c://temp_age//";
        }

        //Criando um File com o endereco do arquivo
        f = new File(path, nomeArqNew);

        OutputStream os = null;
        InputStream is = null;
        try {
            is = uf.getInputstream();
            byte[] b = new byte[is.available()];
            os = new FileOutputStream(f);
            while (is.read(b) > 0) {
                os.write(b);
            }
            // aqui você pode colocar a gravação do path no BD
            anexo = new Anexo();
            anexo.setAtendimento(atendimento);
            anexo.setNomeArquivo(nomeArqNew);
            anexo.setNomeFantasia(nomeArqOld);
            anexo.setPath(path);
            anexosList.add(anexo);

            info("Arquivo enviado");
        } catch (IOException ex) {
            error("Erro");
        } finally {
            try {
                os.flush();
                os.close();
                is.close();
            } catch (IOException ex) {
                error("Erro no envio");
            }
        }

    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public boolean isDesabilitado() {
        return desabilitado;
    }

    public void setDesabilitado(boolean desabilitado) {
        this.desabilitado = desabilitado;
    }

    public List<Atendimento> getMyAtendimentoList() {
        return myAtendimentoList;
    }

    public void setMyAtendimentoList(List<Atendimento> myAtendimentoList) {
        this.myAtendimentoList = myAtendimentoList;
    }

    public List<Atendimento> getOursAtendimentoList() {
        return oursAtendimentoList;
    }

    public void setOursAtendimentoList(List<Atendimento> oursAtendimentoList) {
        this.oursAtendimentoList = oursAtendimentoList;
    }

    public List<Atendimento> getTheirAtendimentoList() {
        return theirAtendimentoList;
    }

    public void setTheirAtendimentoList(List<Atendimento> theirAtendimentoList) {
        this.theirAtendimentoList = theirAtendimentoList;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void upload() {
        info("Sucesso" + file.getFileName());
    }

    public List<Anotacao> getAnotacoesAgendaList() {
        return anotacoesAgendaList;
    }

    public void setAnotacoesAgendaList(List<Anotacao> anotacoesAgendaList) {
        this.anotacoesAgendaList = anotacoesAgendaList;
    }

    public Anotacao getRelatoCliente() {
        return relatoCliente;
    }

    public void setRelatoCliente(Anotacao relatoCliente) {
        this.relatoCliente = relatoCliente;
    }

    public Anotacao getProvidencias() {
        return providencias;
    }

    public void setProvidencias(Anotacao providencias) {
        this.providencias = providencias;
    }

    public Anotacao getAnotacaoSuporte() {
        return anotacaoSuporte;
    }

    public void setAnotacaoSuporte(Anotacao anotacaoSuporte) {
        this.anotacaoSuporte = anotacaoSuporte;
    }

    public List<Evento> getEventoList() {

        if (eventoList == null) {
            eventoList = eventoRN.todos();
        }

        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public List<Evento> getEventoSelectedList() {
        return eventoSelectedList;
    }

    public void setEventoSelectedList(List<Evento> eventoSelectedList) {
        this.eventoSelectedList = eventoSelectedList;
    }

    public String getFalouCom() {
        return falouCom;
    }

    public void setFalouCom(String falouCom) {
        this.falouCom = falouCom;
    }

    public boolean isParaAnalise() {
        return paraAnalise;
    }

    public void setParaAnalise(boolean paraAnalise) {
        this.paraAnalise = paraAnalise;
    }

    public StreamedContent getDownloadFile() {

        try {
            InputStream stream = new FileInputStream(f);
            downloadFile = new DefaultStreamedContent(stream, null, nomeArqNew);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AtendimentoSuporteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return downloadFile;
    }

    public void setDownloadFile(StreamedContent downloadFile) {
        this.downloadFile = downloadFile;
    }

    public List<Anexo> getAnexosList() {
        return anexosList;
    }

    public void setAnexosList(List<Anexo> anexosList) {
        this.anexosList = anexosList;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public FileUtils getFileUtils() {
        return fileUtils;
    }

    public void setFileUtils(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }

}
