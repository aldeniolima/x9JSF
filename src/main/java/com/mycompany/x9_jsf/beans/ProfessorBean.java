/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.beans;

import com.mycompany.x9_jsf.entidades.Professor;
import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
import com.mycompany.x9_jsf.servico.ProfessorServico;
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
public class ProfessorBean extends Bean<Professor> implements Serializable {

    @EJB
    private ProfessorServico servicoProfessor;

    private List<Professor> professores;

    private Professor professorEditar;

    private Professor professorExcluir;

    @Override
    protected void iniciarCampos() {
        setEntidade(servicoProfessor.criar());
    }

    public List<Professor> getProfessores() {
      //  if (professores == null) {
            professores = servicoProfessor.getProfessores();
     //   }
        return professores;
    }

    @Override
    protected boolean salvar(Professor entidade) throws ExcecaoNegocio {
        servicoProfessor.salvar(entidade);
        return true;
    }

    @Override
    protected boolean remover(Professor entidade) throws ExcecaoNegocio {
        servicoProfessor.remover(entidade);
        return true;
    }

    @Override
    protected boolean atualizar(Professor entidade) throws ExcecaoNegocio {
        servicoProfessor.atualizar(professorEditar);
        return true;
    }

    public Professor getProfessorEditar() {
        return professorEditar;
    }

    public String setProfessorEditar(Professor professorEditar) {
        this.professorEditar = professorEditar;
        return "editarProf";
    }

    public Professor getProfessorExcluir() {
        return professorExcluir;
    }

    public void setProfessorExcluir(Professor professorExcluir) throws ExcecaoNegocio {
        this.professorExcluir = professorExcluir;

        remover(this.professorExcluir);
    }
}
