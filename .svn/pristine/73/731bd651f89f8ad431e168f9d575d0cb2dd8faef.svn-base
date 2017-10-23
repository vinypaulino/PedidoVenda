package com.naja.rn;

import com.naja.model.Pessoa;
import java.util.Date;
import java.util.List;
import com.naja.util.DAOException;
import com.naja.util.RNException;
import com.naja.util.RegraNegocio;
import com.naja.util.jpa.Transactional;
import com.naja.reposity.Pessoas;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

public class PessoaRN extends RegraNegocio implements Serializable {

    @Inject
    private Pessoas pessoas;

    @Transactional
    public void salvar(Pessoa pessoa) throws RNException {
        pessoa.setNome(pessoa.getNome().toUpperCase());
        pessoa.setEmail(pessoa.getEmail().toLowerCase());
        pessoa.setDataCadastro(new Date());
//		pessoa.setCidade(pessoa.getEnderecos().getCidade().toUpperCase());
//		pessoa.setBairro(pessoa.getBairro().toUpperCase());
//		pessoa.setRua(pessoa.getRua().toUpperCase());

        Long id = pessoa.getId();

        if (id == null || id == 0) {
            try {
                this.pessoas.salvar(pessoa);
                setLog("Cadastrou a pessoa " + pessoa.getNome());
            } catch (DAOException ex) {
                Logger.getLogger(PessoaRN.class.getName()).log(Level.SEVERE, null, ex);
                throw new RNException("Erro ao cadastrar pessoa.", ex);
            }

        } else {
            System.out.println("Tentativa de atualização do objeto");
            //this.pessoaDAO.atualizar();
        }
    }

    public void excluir(Pessoa pessoa) throws RNException {
        try {
            this.pessoas.excluir(pessoa);
        } catch (DAOException ex) {
            Logger.getLogger(PessoaRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Pessoa> todas() throws RNException {
        try {
            return this.pessoas.todas();
        } catch (DAOException ex) {
            Logger.getLogger(PessoaRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Pessoa porId(Long id) throws RNException {
        try {
            return this.pessoas.porId(id);
        } catch (DAOException ex) {
            Logger.getLogger(PessoaRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Boolean isExiste(String cpfCnpj) {
        System.out.println("Regra de negocio");
        try {
            return this.pessoas.isExiste(cpfCnpj);
        } catch (Exception ex) {
            Logger.getLogger(PessoaRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int countAll(Map filtros) {
        return pessoas.countAll(filtros);
    }

    public List<Pessoa> buscaPorPaginacao(Map criteriosDaBusca,Map filtrosPrimefaces) {
        return pessoas.buscaPorPaginacao(criteriosDaBusca, filtrosPrimefaces);
    }

}