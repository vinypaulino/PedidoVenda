package com.naja.controller;

import com.naja.model.Grupo;
import java.io.Serializable;
import com.naja.model.Log;
import com.naja.model.Menu;
import com.naja.model.Usuario;
import com.naja.reposity.Menus;
import com.naja.reposity.Usuarios;
import com.naja.rn.LogRN;
import com.naja.security.UsuarioSistema;
import com.naja.util.DAOException;
import com.naja.util.RNException;
import com.naja.util.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@SessionScoped
public class ContextoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private LogRN logRN;

    @Inject
    private Usuario usuario;

    @Inject
    private Menus menus;

    @Inject
    Usuarios usuarios;

    private MenuModel menuModel;

    @PostConstruct
    public void init() {
        List<Menu> todosMenuRootDoUsuario = new ArrayList<>();
        menuModel = new DefaultMenuModel();
        try {

            //Preenchendo objeto Usuario com usuario logado
            this.getUsuario();

            //Buscando os grupos do usuario
            //Para nao ocorrer o erro do LAZY faço uma busca no banco pelo USUARIO
            Usuario u = usuarios.porId(usuario.getId());

            //Pegando os Grupos do usuario LOGADO
            List<Grupo> grupos = u.getGrupos();

            //Montando o ArrayList apenas com os menus raiz
            for (Grupo gp : grupos) {
                
                //Peganto TODOS os menus de cada grupo que o usuario pertence
                for (Menu m : gp.getMenus()) {
                    
                    //Verificando se este menu é PAI
                    if (m.getPaiId() == null) {
                        
                        //Se for PAI e ainda NÂO estiver SIDO usado                        
                        if (!todosMenuRootDoUsuario.contains(m)) {
                            
                            //Adicionando dentro do ARRAY apenas para Verificar se não foi usado
                            todosMenuRootDoUsuario.add(m);

                            DefaultSubMenu menuRoot = new DefaultSubMenu(m.getNome());
                            menuRoot.setIcon(m.getIcone());
                            menuRoot.setStyle("style='margin-bottom: 1px;'");

                            //Trazer todos os filhos deste menu que este grupo tem acesso apenas
                            List<Menu> menusList = menus.todosFilhos(m,gp);

                            for (Menu menuFilho : menusList) {
                                DefaultMenuItem item = new DefaultMenuItem(menuFilho.getNome());
                                item.setUrl(menuFilho.getUrl());
                                item.setIcon(menuFilho.getIcone());                                
                                menuRoot.addElement(item);
                            }

                            menuModel.addElement(menuRoot);
                        }
                    }
                }
            }

        } catch (DAOException ex) {
            Logger.getLogger(ContextoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setLog(String msg) {

        Log log = new Log();
        log.setDataLog(new Date());
        log.setIp(Util.pegarIp());
        log.setLinkAcesso(Util.pegarURL());
        log.setUsuario(getUsuarioLogadoCDI().getUsuario());
        log.setDescricao(msg);

        try {
            logRN.salvar(log);
        } catch (RNException ex) {
            Logger.getLogger(ContextoBean.class.getName()).log(Level.SEVERE, "Erro ao salvar o LOG no ContextoBean", ex);
        }

    }

    private UsuarioSistema getUsuarioLogadoCDI() {

        UsuarioSistema usuario = null;

        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (auth != null && auth.getPrincipal() != null) {
            usuario = (UsuarioSistema) auth.getPrincipal();
        }

        return usuario;
    }

    public Usuario getUsuario() {
        this.usuario = getUsuarioLogadoCDI().getUsuario();
        return this.usuario;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }
}
