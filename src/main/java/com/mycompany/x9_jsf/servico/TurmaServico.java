
package com.mycompany.x9_jsf.servico;

import static javax.ejb.TransactionManagementType.CONTAINER;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;

import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
import com.mycompany.x9_jsf.entidades.Turma;
import static com.mycompany.x9_jsf.excecao.ExcecaoNegocio.REMOVER_TURMA;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

/**
 *
 * @author joselima
 */
@Stateless
@LocalBean
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
public class TurmaServico extends Servico<Turma> {   
    
    public void salvar(Turma turma) throws ExcecaoNegocio {
        //checarExistencia(Turma.TURMA_POR_SALA_E_TURNO, new Object[] {turma.getTurno(), turma.getNumeroSala()});
        entityManager.persist(turma);
    }
     
    public void atualizar(Turma turma) throws ExcecaoNegocio {
        //checarExistencia(Turma.TURMA_POR_SALA_E_TURNO, new Object[] {turma.getTurno(), turma.getNumeroSala()});
        checarNaoExistencia(Turma.TURMA_POR_SERIE_E_ID,  new Object[] {turma.getSerie(), turma.getIdTurma()});
        entityManager.merge(turma);
        entityManager.flush();
    }    
    
    public void remover(Turma turma) throws ExcecaoNegocio {
        turma = entityManager.merge(turma);
        if (turma.isInativo())
            entityManager.remove(turma);
        else
            throw new ExcecaoNegocio(REMOVER_TURMA);
    }
    
    public void remover(String serie, String turno) throws ExcecaoNegocio {        
        Turma turma = getTurma(serie, turno);
        remover(turma);
    }    
    
    @TransactionAttribute(SUPPORTS)   
    @PermitAll
    public List<Turma> getTurmas() {
        return getEntidades(Turma.TURMAS);
    }
    
    @TransactionAttribute(SUPPORTS)   
    @PermitAll     
    public Turma getTurma(String serie, String turno) {
        return super.getEntidade(Turma.TURMA_POR_SERIE_E_TURNO, new Object[] {serie, turno});
    }

    @TransactionAttribute(SUPPORTS)   
    @PermitAll
    public Turma criar() {
        return new Turma();
    }
}
