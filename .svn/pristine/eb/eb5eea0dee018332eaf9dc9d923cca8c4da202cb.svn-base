/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.rn;

import com.naja.model.Grupo;
import com.naja.model.Menu;
import com.naja.reposity.Grupos;
import com.naja.reposity.Menus;
import com.naja.util.DAOException;
import com.naja.util.RegraNegocio;
import com.naja.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.util.StringUtils;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
public class MenuRN extends RegraNegocio implements Serializable {

    @Inject
    private Menus menus;
    
    @Inject 
    private Grupos grupos;
    
    @Transactional
    public void salvar(Menu menu) {
        try {
            menu.setNome(StringUtils.capitalize(menu.getNome()));
            menu.setIcone(menu.getIcone().toLowerCase());
            menu.setUrl(menu.getUrl().toLowerCase());
            menus.salvar(menu);            
        } catch (DAOException ex) {
            Logger.getLogger(MenuRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Menu> todosRoot() {
        try {
            return menus.todosRoot();
        } catch (DAOException ex) {
            Logger.getLogger(MenuRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Menu> todosFilhos(Menu menuPai) {
        try {
            return menus.todosFilhos(menuPai);
        } catch (DAOException ex) {
            Logger.getLogger(MenuRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
