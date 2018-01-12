/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.beans;

import com.mycompany.x9_jsf.entidades.Aluno;
import com.mycompany.x9_jsf.entidades.Professor;
import com.mycompany.x9_jsf.entidades.Turma;
import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
import com.mycompany.x9_jsf.servico.AlunoServico;
import com.mycompany.x9_jsf.servico.ProfessorServico;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.mycompany.x9_jsf.servico.TurmaServico;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author joselima
 */
@ManagedBean
@SessionScoped
public class TurmaBean extends Bean<Turma> implements Serializable {

    @EJB
    private TurmaServico servicoTurma;
    @EJB
    private ProfessorServico servicoProfessor;

    @EJB
    private AlunoServico alunoServico;

    private List<Aluno> alunosPorTurma;

    private List<Turma> turmas;

    private FacesContext facesContext;

    private Turma turmaSelecionada;
    
    private Turma turmaEditar;
    
    private Turma turmaExcluir;

    @Override
    protected void iniciarCampos() {
        setEntidade(servicoTurma.criar());
    }

    public List<Turma> getTurmas() {
      //  if (turmas == null) {
            turmas = servicoTurma.getTurmas();
      //  }
        return turmas;
    }

    public List<Professor> getProfessores() {
        return servicoProfessor.getProfessores();
    }

    @Override
    protected boolean salvar(Turma entidade) throws ExcecaoNegocio {
        servicoTurma.salvar(entidade);
        return true;
    }
    
    @Override
    protected boolean atualizar(Turma entidade) throws ExcecaoNegocio {
        servicoTurma.atualizar(turmaEditar);
        return true;
    }

    @Override
    protected boolean remover(Turma entidade) throws ExcecaoNegocio {
        servicoTurma.remover(entidade);
        return true;
    }

    public String setTurmaSelecionada(Turma turmaSelecionada) throws IOException {
        this.turmaSelecionada = turmaSelecionada;
        /* facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        FacesContext.getCurrentInstance().getExternalContext().dispatch("../professor/listarAlunos.xhtml");*/
        return "listarAlunosProf";
    }

    public Turma getTurmaSelecionada() {
        return turmaSelecionada;
    }

    public List<Aluno> alunosDaTurma(Turma turma) {
        alunosPorTurma = alunoServico.getAlunosPorTurma(turmaSelecionada);
        return alunosPorTurma;
    }

    public List<Aluno> getAlunosPorTurma() {
        alunosPorTurma = alunoServico.getAlunosPorTurma(turmaSelecionada);
        return alunosPorTurma;
    }

    public void setAlunosPorTurma(List<Aluno> alunosPorTurma) {
        this.alunosPorTurma = alunosPorTurma;
    }

    public Turma getTurmaEditar() {
        return turmaEditar;
    }

    public String setTurmaEditar(Turma turmaEditar) {
        this.turmaEditar = turmaEditar;
        
        return "editarTurma";
    }

    public Turma getTurmaExcluir() {
        return turmaExcluir;
    }

    public void setTurmaExcluir(Turma turmaExcluir) throws ExcecaoNegocio{
        this.turmaExcluir = turmaExcluir;
        remover(this.turmaExcluir);
    }
}
