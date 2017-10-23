/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.reposity;

import com.naja.model.Tarefa;
import com.naja.util.DAOException;
import java.io.Serializable;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class Tarefas extends DAO implements Serializable {

    public void salvar(Tarefa t) throws DAOException {
        this.em.persist(t);
    }
}
