/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.naja.reposity;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com 
 * NajaSoftware
 */
public class DAO {
    
    private static final long serialVersionUID = 1L;

    @Inject
    protected EntityManager em;
    
}
