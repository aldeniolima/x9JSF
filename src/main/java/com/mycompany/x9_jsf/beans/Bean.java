
package com.mycompany.x9_jsf.beans;

import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
import com.mycompany.x9_jsf.excecao.MensagemExcecao;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolationException;

public abstract class Bean<T> {

    protected T entidade;

    @PostConstruct
    public void init() {
        iniciarCampos();
    }

    protected abstract void iniciarCampos();

    protected abstract boolean salvar(T entidade) throws ExcecaoNegocio;
    
    protected abstract boolean atualizar(T entidade) throws ExcecaoNegocio;

    protected abstract boolean remover(T entidade) throws ExcecaoNegocio;


    public T getEntidade() {
        return entidade;
    }

    public void setEntidade(T entidade) {
        this.entidade = entidade;
    }

    public void salvar() {
        try {
            boolean sucesso = salvar(entidade);
            if (sucesso) {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!");
            }
        } catch (ExcecaoNegocio ex) {
            adicionarMessagem(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (EJBException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ex.getCause());
                adicionarMessagem(FacesMessage.SEVERITY_WARN, mensagemExcecao.getMensagem());
            } else {
                throw ex;
            }
        } finally {
            iniciarCampos();
        }
    }
    
    public void remover() {
        try {
            boolean sucesso = remover(entidade);
            if (sucesso) {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Excluído com sucesso!");
            }
        } catch (ExcecaoNegocio ex) {
            adicionarMessagem(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (EJBException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ex.getCause());
                adicionarMessagem(FacesMessage.SEVERITY_WARN, mensagemExcecao.getMensagem());
            } else {
                throw ex;
            }
        } finally {
            iniciarCampos();
        }
    }

    public void atualizar() {
        try {
            boolean sucesso = atualizar(entidade);
            if (sucesso) {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!");
            }
        } catch (ExcecaoNegocio ex) {
            adicionarMessagem(FacesMessage.SEVERITY_WARN, ex.getMessage());
        } catch (EJBException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ex.getCause());
                adicionarMessagem(FacesMessage.SEVERITY_WARN, mensagemExcecao.getMensagem());
            } else {
                throw ex;
            }
        } finally {
            iniciarCampos();
        }
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem) {
        FacesMessage message = new FacesMessage(severity, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
