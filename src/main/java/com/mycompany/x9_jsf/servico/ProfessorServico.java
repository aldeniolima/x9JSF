/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.servico;

import com.mycompany.x9_jsf.entidades.Endereco;
import com.mycompany.x9_jsf.entidades.Professor;
import com.mycompany.x9_jsf.entidades.Usuario;
import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
import static com.mycompany.x9_jsf.excecao.ExcecaoNegocio.REMOVER_PROFESSOR;
import static javax.ejb.TransactionManagementType.CONTAINER;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;

/**
 *
 * @author joselima
 */
@Stateless
@LocalBean
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED)
public class ProfessorServico extends Servico<Professor> {

    public void salvar(Professor professor) throws ExcecaoNegocio {
        checarExistencia(Professor.PROFESSOR_POR_CPF, professor.getCpf());
        checarExistencia(Professor.PROFESSOR_POR_EMAIL, professor.getEmail());
        checarExistencia(Usuario.USUARIO_POR_LOGIN, professor.getLogin());
        entityManager.persist(professor);
    }

    public void atualizar(Professor professor) throws ExcecaoNegocio {
        checarNaoExistencia(Professor.PROFESSOR_POR_CPF_E_ID, new Object[]{professor.getCpf(), professor.getIdUsuario()});
        entityManager.merge(professor);
        entityManager.flush();
    }

    public void remover(Professor professor) throws ExcecaoNegocio {
        professor = entityManager.merge(professor);
        if (professor.isInativo()) {
            entityManager.remove(professor);
        } else {
            throw new ExcecaoNegocio(REMOVER_PROFESSOR);
        }
    }

    public void remover(String cpf) throws ExcecaoNegocio {
        Professor professor = getProfessor(cpf);
        remover(professor);
    }

    @TransactionAttribute(SUPPORTS)
    public List<Professor> getProfessores() {
        return getEntidades(Professor.PROFESSORES);
    }

    @TransactionAttribute(SUPPORTS)
    public Professor getProfessor(String cpf) {
        return super.getEntidade(Professor.PROFESSOR_POR_CPF, new Object[]{cpf});
    }

    @TransactionAttribute(SUPPORTS)
    public Professor criar() {
        Endereco endereco = new Endereco();
        Professor professor = new Professor();
        professor.setEndereco(endereco);
        return professor;
    }
}
