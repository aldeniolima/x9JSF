package com.mycompany.x9_jsf.servico;

import com.mycompany.x9_jsf.entidades.Endereco;
import com.mycompany.x9_jsf.entidades.Responsavel;
import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
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
public class ResponsavelServico extends Servico<Responsavel> {

    public void salvar(Responsavel responsavel) throws ExcecaoNegocio {
        checarExistencia(Responsavel.RESPONSAVEL_POR_CPF, responsavel.getCpf());
        checarExistencia(Responsavel.RESPONSAVEL_POR_EMAIL, responsavel.getEmail());
        entityManager.persist(responsavel);
    }

    public void atualizar(Responsavel responsavel) throws ExcecaoNegocio {
        checarNaoExistencia(Responsavel.RESPONSAVEL_POR_CPF_E_ID, new Object[]{responsavel.getCpf(), responsavel.getIdUsuario()});
        entityManager.merge(responsavel);
        entityManager.flush();
    }

    public void remover(Responsavel responsavel) throws ExcecaoNegocio {
        responsavel = entityManager.merge(responsavel);
        if (responsavel.isInativo()) {
            entityManager.remove(responsavel);
        } else {
            throw new ExcecaoNegocio(ExcecaoNegocio.REMOVER_RESPONSAVEL);
        }
    }

    public void remover(String cpf) throws ExcecaoNegocio {
        Responsavel responsavel = getResponsavel(cpf);
        remover(responsavel);
    }

    @TransactionAttribute(SUPPORTS)
    public List<Responsavel> getResponsaveis() {
        return getEntidades(Responsavel.RESPONSAVEIS);
    }

    @TransactionAttribute(SUPPORTS)
    public Responsavel getResponsavel(String cpf) {
        return super.getEntidade(Responsavel.RESPONSAVEL_POR_CPF, new Object[]{cpf});
    }

    @TransactionAttribute(SUPPORTS)
    public Responsavel criar() {
        Endereco endereco = new Endereco();
        Responsavel responsavel = new Responsavel();
        responsavel.setEndereco(endereco);
        return responsavel;
    }
}
