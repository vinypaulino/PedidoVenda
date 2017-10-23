/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naja.controller;

import com.naja.model.Grupo;
import com.naja.model.Menu;
import com.naja.rn.GrupoRN;
import com.naja.rn.MenuRN;
import com.naja.util.Controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Named
@ViewScoped
public class CadMenuController extends Controller implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Menu menu;

    @Inject
    private MenuRN menuRN;

    @Inject
    private GrupoRN grupoRN;

    @Inject
    private Grupo grupo;

    private List<Menu> menuList;
    private List<Grupo> grupoList;
    
    @PostConstruct
    public void init() {
        menu = new Menu();
        menuList = menuRN.todosRoot();
    }

    public List<Menu> menuFilho(Menu menuPai) {
        List<Menu> menusFilhos = menuRN.todosFilhos(menuPai);
        return menusFilhos;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    public void salvar() {

        for (Grupo gp : menu.getGrupos()) {            
            gp.getMenus().add(menu);
        }

        menuRN.salvar(this.menu);
        init();
        info("Menu Salvo com sucesso");
    }

    public List<Grupo> getGrupoList() {

        if (grupoList == null) {
            grupoList = grupoRN.todos();
        }

        return grupoList;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

}