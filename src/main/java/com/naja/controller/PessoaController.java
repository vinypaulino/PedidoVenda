package com.naja.controller;

import com.naja.model.Pessoa;
import com.naja.reposity.Pessoas;
import com.naja.rn.PessoaRN;
import com.naja.util.Controller;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Named
@ViewScoped
public class PessoaController extends Controller implements Serializable {

    @Inject
    private PessoaRN pessoaRN;
    
    @Inject
    private Pessoas pessoas;

    private LazyDataModel<Pessoa> model;
    private String id;
    private String nome;
    private String nomeFantasia;
    private String cnpj;
    private String email;
    private String ie;
    private boolean status;
    private Map mapCriteriosBusca = new HashMap();
    private int totalPessoa;
    private boolean buscar;

    /*
    Inicializa as variaveis.
     */
    @PostConstruct
    public void init() {
        
        buscar = false;
        status = true;
        
        model = new LazyDataModel<Pessoa>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<Pessoa> load(int posicaoPrimeiraLinha, int maximoPorPagina, String ordernarPeloCampo, SortOrder ordernarAscOuDesc, Map<String, Object> filtros) {

                String ordenacao = ordernarAscOuDesc.toString();
                
                if (StringUtils.isNotBlank(nomeFantasia)){
                    buscar = true;
                } else if (StringUtils.isNotBlank(nome)){
                    buscar = true;
                } else if(id != null){
                    buscar = true;
                } else if(StringUtils.isNotBlank(email)){
                    buscar = true;
                } else if (StringUtils.isNotBlank(ie)){
                    buscar = true;
                } else if (StringUtils.isNotBlank(cnpj)) {
                    buscar = true;
                }
                
                if (buscar) {

                    if (SortOrder.UNSORTED.equals(ordernarAscOuDesc)) {
                        ordenacao = SortOrder.ASCENDING.toString();
                    }

                    mapCriteriosBusca.put("posicaoPrimeiraLinha", posicaoPrimeiraLinha);
                    mapCriteriosBusca.put("maximoPorPagina", maximoPorPagina);
                    mapCriteriosBusca.put("ordernarPeloCampo", ordernarPeloCampo);
                    mapCriteriosBusca.put("ordenacao", ordenacao);
                    
                    //Vindos do formulário para buscar
                    mapCriteriosBusca.put("id", id);
                    mapCriteriosBusca.put("status",status);
                    mapCriteriosBusca.put("nome",nome);
                    mapCriteriosBusca.put("nomeFantasia",nomeFantasia);
                    mapCriteriosBusca.put("email",email);
                    mapCriteriosBusca.put("ie",ie);
                    mapCriteriosBusca.put("cnpj",cnpj);

                    if (getRowCount() <= 0 || (filtros != null && !filtros.isEmpty())) {
                        setRowCount(pessoaRN.countAll(filtros));
                    }

                    // quantidade a ser exibida em cada página 
                    setPageSize(maximoPorPagina);

                    List<Pessoa> temp = pessoaRN.buscaPorPaginacao(mapCriteriosBusca, filtros);

                    totalPessoa = temp.size();

                    return temp;
                    
                } else {                    
                    return null;
                }
            }

        };
    }

    public LazyDataModel<Pessoa> getModel() {
        return model;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public int getTotalPessoa() {
        return totalPessoa;
    }

    public void setTotalPessoa(int totalPessoa) {
        this.totalPessoa = totalPessoa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
