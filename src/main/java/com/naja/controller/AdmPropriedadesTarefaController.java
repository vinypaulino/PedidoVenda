package com.naja.controller;

import com.naja.model.TarefaAtividade;
import com.naja.model.TarefaEstado;
import com.naja.model.TarefaPrioridade;
import com.naja.model.TarefaTipo;
import com.naja.rn.TarefaAtividadeRN;
import com.naja.rn.TarefaEstadoRN;
import com.naja.rn.TarefaPrioridadeRN;
import com.naja.rn.TarefaTipoRN;
import com.naja.util.Controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Named
@ViewScoped
public class AdmPropriedadesTarefaController extends Controller implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TarefaEstadoRN tarefaEstadoRN;

    @Inject
    private TarefaTipoRN tarefaTipoRN;

    @Inject
    private TarefaPrioridadeRN tarefaPrioridadeRN;

    @Inject
    private TarefaAtividadeRN tarefaAtividadeRN;

    @Inject
    private TarefaEstado tarefaEstado;

    @Inject
    private TarefaTipo tarefaTipo;

    @Inject
    private TarefaPrioridade tarefaPrioridade;
    
    @Inject
    private TarefaAtividade tarefaAtividade;

    private List<TarefaEstado> tarefaEstadoList;
    private List<TarefaTipo> tarefaTipoList;
    private List<TarefaPrioridade> tarefaPrioridadesList;
    private List<TarefaAtividade> tarefaAtividadeList;

    private int i;

    @PostConstruct
    public void init() {
        i = 0;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public List<TarefaEstado> getTarefaEstadoList() {
        if (tarefaEstadoList == null) {
            tarefaEstadoList = tarefaEstadoRN.todos();
        }
        return tarefaEstadoList;
    }

    public List<TarefaTipo> getTarefaTipoList() {
        if (tarefaTipoList == null) {
            tarefaTipoList = tarefaTipoRN.todos();
        }
        return tarefaTipoList;
    }

    public List<TarefaPrioridade> getTarefaPrioridadesList() {
        if (tarefaPrioridadesList == null) {
            tarefaPrioridadesList = tarefaPrioridadeRN.todas();
        }
        return tarefaPrioridadesList;
    }

    public List<TarefaAtividade> getTarefaAtividadeList() {
        if (tarefaAtividadeList == null) {
            tarefaAtividadeList = tarefaAtividadeRN.todas();
        }
        return tarefaAtividadeList;
    }

    public void salvarEstado() {
        tarefaEstadoRN.salvar(tarefaEstado);
        tarefaEstadoList = null;
        tarefaEstado = new TarefaEstado();
    }

    public void deletarEstado() {
        tarefaEstadoRN.deletar(tarefaEstado);
        tarefaEstadoList = null;
        tarefaEstado = new TarefaEstado();
    }

    public void salvarTipo() {
        tarefaTipoRN.salvar(tarefaTipo);
        tarefaTipoList = null;
        tarefaTipo = new TarefaTipo();
    }
    
    public void deletarPrioridade() {        
        tarefaPrioridadeRN.deletar(tarefaPrioridade);
        tarefaPrioridadesList = null;
        tarefaPrioridade = new TarefaPrioridade();
    }

    public void deletarTipo() {
        tarefaTipoRN.deletar(tarefaTipo);
        tarefaTipoList = null;
        tarefaTipo = new TarefaTipo();
    }
    
    public void deletarAtividade() {
        tarefaAtividadeRN.deletar(tarefaAtividade);
        tarefaAtividadeList = null;
        tarefaAtividade = new TarefaAtividade();
    }

    public void salvarPrioridade() {
        tarefaPrioridadeRN.salvar(tarefaPrioridade);
        tarefaPrioridadesList = null;
        tarefaPrioridade = new TarefaPrioridade();
    }
    public void salvarAtividade() {
        tarefaAtividadeRN.salvar(tarefaAtividade);
        tarefaAtividadeList = null;
        tarefaAtividade = new TarefaAtividade();
    }

    public TarefaEstado getTarefaEstado() {
        return tarefaEstado;
    }

    public void setTarefaEstado(TarefaEstado tarefaEstado) {
        this.tarefaEstado = tarefaEstado;
    }

    public TarefaTipo getTarefaTipo() {
        return tarefaTipo;
    }

    public void setTarefaTipo(TarefaTipo tarefaTipo) {
        this.tarefaTipo = tarefaTipo;
    }

    public TarefaPrioridade getTarefaPrioridade() {
        return tarefaPrioridade;
    }

    public void setTarefaPrioridade(TarefaPrioridade tarefaPrioridade) {
        this.tarefaPrioridade = tarefaPrioridade;
    }

    public TarefaAtividade getTarefaAtividade() {
        return tarefaAtividade;
    }

    public void setTarefaAtividade(TarefaAtividade tarefaAtividade) {
        this.tarefaAtividade = tarefaAtividade;
    }
       

}
