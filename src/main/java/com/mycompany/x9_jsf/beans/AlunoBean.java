/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.beans;

import com.mycompany.x9_jsf.entidades.Aluno;
import com.mycompany.x9_jsf.entidades.RelatorioParental;
import com.mycompany.x9_jsf.entidades.Turma;
import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
import com.mycompany.x9_jsf.servico.AlunoServico;
import com.mycompany.x9_jsf.servico.RelatorioServico;
import com.mycompany.x9_jsf.servico.TurmaServico;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author joselima
 */
@ManagedBean
@SessionScoped
public class AlunoBean extends Bean<Aluno> implements Serializable {

    @EJB
    private AlunoServico servicoAluno;
    @EJB
    private TurmaServico servicoTurma;
    @EJB
    private RelatorioServico servicoRelatorio;

    private List<Aluno> alunos;

    private Aluno alunoSelecionado;

    private FacesContext facesContext;

    private Aluno alunoEditar;

    private Aluno alunoExcluir;

    private RelatorioParental relatorioEditado;
    
    private Aluno alunoResp;

    @Override
    protected void iniciarCampos() {
        setEntidade(servicoAluno.criar());
    }

    public List<Aluno> getAlunos() {
        // if (alunos == null) {
        alunos = servicoAluno.getAlunos();
        //   }
        return alunos;
    }

    public List<Turma> getTurmas() {
        return servicoTurma.getTurmas();
    }

    @Override
    protected boolean salvar(Aluno entidade) throws ExcecaoNegocio {
        servicoAluno.salvar(entidade);
        return true;
    }

    @Override
    protected boolean remover(Aluno entidade) throws ExcecaoNegocio {
        servicoAluno.remover(entidade);
        return true;
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public String setAlunoSelecionado(Aluno alunoSelecionado) throws IOException {
        this.alunoSelecionado = alunoSelecionado;
        /* facesContext = FacesContext.getCurrentInstance();
        FacesContext.getCurrentInstance().getExternalContext().dispatch("../professor/listarNotas.xhtml");*/
        return "listarNotasProf";
    }

    @Override
    protected boolean atualizar(Aluno entidade) throws ExcecaoNegocio {
        servicoAluno.atualizar(alunoEditar);
        return true;
    }

    protected boolean atualizarRelatorio(RelatorioParental entidade) throws ExcecaoNegocio {
        servicoRelatorio.atualizar(entidade);
        return true;
    }

    public Aluno getAlunoEditar() {
        return alunoEditar;
    }

    public String setAlunoEditar(Aluno alunoEditar) {
        this.alunoEditar = alunoEditar;
        return "editarAluno";
    }

    public Aluno getAlunoExcluir() {
        return alunoExcluir;
    }

    public void setAlunoExcluir(Aluno alunoExcluir) throws ExcecaoNegocio {
        this.alunoExcluir = alunoExcluir;

        remover(this.alunoExcluir);
    }

    public RelatorioParental getRelatorioEditado() {
        return relatorioEditado;
    }

    public void setRelatorioEditado(RelatorioParental relatorioEditado) throws ExcecaoNegocio {
        this.relatorioEditado = relatorioEditado;
        atualizarRelatorio(relatorioEditado);
    }

    public Aluno getAlunoResp() {
        return alunoResp;
    }

    public String setAlunoResp(Aluno alunoResp) {
        this.alunoResp = alunoResp;
        
        return "notas";
    }
}
