package com.naja.controller;

import java.io.Serializable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.naja.model.Pessoa;
import com.naja.util.Controller;
import com.naja.rn.PessoaRN;
import com.naja.util.RNException;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
/**
 * @author Lemoel
 */
public class CadPessoaController extends Controller implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Pessoa pessoa;

    @Inject
    private PessoaRN pessoaRN;
    
    private String telefone;
    private Set<String> telefones;

    @PostConstruct
    public void inicializar() {
        this.pessoa = new Pessoa();
        this.telefones = new HashSet<String>();
        this.telefone = new String();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public void addTelefone() {

        if (telefone.length() == 13) {
            if (telefones.contains(telefone)) {
                telefone = null;
            } else {
                telefones.add(telefone);
                telefone = null;
            }
        } else {
            error("Erro ao digitar o telefone!!!");
        }
    }

    public void salvar() {

        if (telefone.length() == 13) {
            if (!telefones.contains(telefone)) {
                telefones.add(telefone);
            }
        }

        //this.pessoa.setTelefone(telefones);
        this.pessoa.setStatus(true);

        try {
            pessoaRN.salvar(this.pessoa);
            info("Empresa " + this.pessoa.getNome() + " salva com sucesso! " );
        } catch (RNException ex) {
            Logger.getLogger(CadPessoaController.class.getName()).log(Level.SEVERE, null, ex);
            error("Erro ao Salvar Empresa");
        }

        this.inicializar();    
    }
}
