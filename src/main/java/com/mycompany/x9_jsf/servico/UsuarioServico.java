/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.servico;

import static javax.ejb.TransactionManagementType.CONTAINER;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import com.mycompany.x9_jsf.entidades.Usuario;
import com.mycompany.x9_jsf.entidades.Administrador;
import com.mycompany.x9_jsf.entidades.Professor;
import com.mycompany.x9_jsf.entidades.Responsavel;
import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
import javax.annotation.security.PermitAll;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.ejb.TransactionManagement;

@Stateless
@LocalBean
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED)
public class UsuarioServico extends Servico<Usuario> {

    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public Usuario login(String login, String senha) {
        return super.getEntidade(Usuario.USUARIO_POR_LOGIN_E_SENHA, new Object[]{login, senha});
    }
    
    
     public void salvarAdmin(Administrador admin) throws ExcecaoNegocio {
        //checarExistencia(Turma.TURMA_POR_SERIE_E_TURNO, new Object[] {turma.getSerie(), turma.getTurno()});
        entityManager.persist(admin);
    }
     
         
     public void salvarProfessor(Professor prof) throws ExcecaoNegocio {
        //checarExistencia(Turma.TURMA_POR_SERIE_E_TURNO, new Object[] {turma.getSerie(), turma.getTurno()});
        entityManager.persist(prof);
    }
     
     public void salvarResponsavel(Responsavel resp) throws ExcecaoNegocio {
        //checarExistencia(Turma.TURMA_POR_SERIE_E_TURNO, new Object[] {turma.getSerie(), turma.getTurno()});
        entityManager.persist(resp);
    }
     

}
