/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.servico;

import com.mycompany.x9_jsf.entidades.Aluno;
import com.mycompany.x9_jsf.entidades.Responsavel;
import com.mycompany.x9_jsf.entidades.Turma;
import com.mycompany.x9_jsf.entidades.Usuario;

import static javax.ejb.TransactionManagementType.CONTAINER;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;

import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;

@Stateless
@LocalBean
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED)
public class AlunoServico extends Servico<Aluno> {

    public void salvar(Aluno aluno) throws ExcecaoNegocio {
        checarExistencia(Responsavel.RESPONSAVEL_POR_CPF, aluno.getResponsavel().getCpf());
        checarExistencia(Responsavel.RESPONSAVEL_POR_EMAIL, aluno.getResponsavel().getEmail());
        checarExistencia(Usuario.USUARIO_POR_LOGIN, aluno.getResponsavel().getLogin());
        checarExistencia(Aluno.ALUNO_POR_MATRICULA, aluno.getMatricula());
        entityManager.persist(aluno);
    }

    public void atualizar(Aluno aluno) throws ExcecaoNegocio {
        checarNaoExistencia(Aluno.ALUNO_POR_MATRICULA_E_ID, new Object[]{aluno.getMatricula(), aluno.getIdAluno()});
        entityManager.merge(aluno);
        entityManager.flush();
    }

    public void remover(Aluno aluno) throws ExcecaoNegocio {
        aluno = entityManager.merge(aluno);
        entityManager.remove(aluno);
    }

    public void remover(String matricula) throws ExcecaoNegocio {
        Aluno aluno = getAluno(matricula);
        remover(aluno);
    }

    @TransactionAttribute(SUPPORTS)
    public List<Aluno> getAlunos() {
        return getEntidades(Aluno.ALUNOS);
    }

    @TransactionAttribute(SUPPORTS)
    public Aluno getAluno(String matricula) {
        return super.getEntidade(Aluno.ALUNO_POR_MATRICULA, new Object[]{matricula});
    }
     @TransactionAttribute(SUPPORTS)
    public List<Aluno> getAlunosPorTurma(Turma turma) {
        return getEntidades(Aluno.ALUNO_POR_TURMA, new Object[]{turma});
    }

    @TransactionAttribute(SUPPORTS)
    public Aluno criar() {
        Aluno aluno = new Aluno();
        aluno.criarRelatorio();
        Responsavel responsavel = aluno.criarResponsavel();
        responsavel.criarEndereco();
        aluno.setResponsavel(responsavel);
        return aluno;
    }
}
