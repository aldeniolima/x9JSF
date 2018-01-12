/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.beans;

import com.mycompany.x9_jsf.entidades.Responsavel;
import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
import com.mycompany.x9_jsf.servico.ResponsavelServico;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author joselima
 */
@ManagedBean
@SessionScoped
public class ResponsavelBean extends Bean<Responsavel> implements Serializable {

    @EJB
    private ResponsavelServico servicoResponsavel;

    private List<Responsavel> responsaveis;

    private Responsavel responsavelEditar;

    private Responsavel responsavelExcluir;

    @Override
    protected void iniciarCampos() {
        setEntidade(servicoResponsavel.criar());
    }

    public List<Responsavel> getResponsaveis() {
//        if (responsaveis == null) {
            responsaveis = servicoResponsavel.getResponsaveis();
 //       }
        return responsaveis;
    }

    @Override
    protected boolean salvar(Responsavel entidade) throws ExcecaoNegocio {
        servicoResponsavel.salvar(entidade);
        return true;
    }

    @Override
    protected boolean remover(Responsavel entidade) throws ExcecaoNegocio {
        servicoResponsavel.remover(entidade);
        return true;
    }

    @Override
    protected boolean atualizar(Responsavel entidade) throws ExcecaoNegocio {
        servicoResponsavel.atualizar(responsavelEditar);
        return true;
    }

    public Responsavel getResponsavelEditar() {
        return responsavelEditar;
    }

    public String setResponsavelEditar(Responsavel responsavelEditar) {
        this.responsavelEditar = responsavelEditar;

        return "editarResp";
    }

    public Responsavel getResponsavelExcluir() {
        return responsavelExcluir;
    }

    public void setResponsavelExcluir(Responsavel responsavelExcluir) throws ExcecaoNegocio {
        this.responsavelExcluir = responsavelExcluir;
        remover(this.responsavelExcluir);
    }
}
