/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.beans;

import com.mycompany.x9_jsf.entidades.Administrador;
import com.mycompany.x9_jsf.entidades.Endereco;
import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import com.mycompany.x9_jsf.servico.UsuarioServico;
import java.util.Calendar;
import java.util.GregorianCalendar;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean extends Bean<Administrador> implements Serializable {

    public UsuarioBean() {
        this.entidade = new Administrador();
    }

    @EJB
    private UsuarioServico usuarioServico;
    private boolean sucesso = true;

    @Override
    protected void iniciarCampos() {
        if (sucesso) {
            this.entidade = new Administrador();
        }
    }

    @Override
    protected boolean salvar(Administrador entidade) {
        this.sucesso = false;
        Endereco end = new Endereco();
        end.setCep("55.800-000");
        end.setCidade("Nazare");
        end.setNumeroEndereco(50);
        end.setRua("Rua do teste ejb");
        end.setUf("PE");
        entidade.setEndereco(end);
      //  usuarioServico.salvar(entidade);
        this.sucesso = true;
        return this.sucesso;
    }

    @Override
    protected boolean remover(Administrador entidade) throws ExcecaoNegocio {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean atualizar(Administrador entidade) throws ExcecaoNegocio {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
