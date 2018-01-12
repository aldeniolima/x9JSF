/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.servico;

import com.mycompany.x9_jsf.entidades.RelatorioParental;

import static javax.ejb.TransactionManagementType.CONTAINER;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;

import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;

@Stateless
@LocalBean
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED)
public class RelatorioServico extends Servico<RelatorioParental> {

    public void salvar(RelatorioParental relatorio) throws ExcecaoNegocio {
        //checarExistencia(Aluno.ALUNO_POR_MATRICULA, aluno.getMatricula());
        entityManager.persist(relatorio);
    }

    public void atualizar(RelatorioParental relatorio) throws ExcecaoNegocio {
       // checarNaoExistencia(Aluno.ALUNO_POR_MATRICULA_E_ID, new Object[]{aluno.getMatricula(), aluno.getIdAluno()});
        entityManager.merge(relatorio);
        entityManager.flush();
    }
}

