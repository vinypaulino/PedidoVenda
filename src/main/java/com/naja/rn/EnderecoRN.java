/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.reposity.Enderecos;
import com.naja.model.Endereco;
import com.naja.util.DAOException;
import com.naja.util.RegraNegocio;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class EnderecoRN extends RegraNegocio implements Serializable {

    @Inject
    private Enderecos enderecos;

    public List<Endereco> buscar() throws DAOException {
        return enderecos.todos();
    }
}
