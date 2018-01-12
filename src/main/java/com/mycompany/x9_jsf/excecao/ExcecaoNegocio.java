package com.mycompany.x9_jsf.excecao;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ExcecaoNegocio extends Exception {
    private String chave;    
    public static final String OBJETO_INEXISTENTE = "com.mycompany.x9_jsf.excecao.ExcecaoNegocio.objetoInexistente";
    public static final String REMOVER_RESPONSAVEL = "com.mycompany.x9_jsf.excecao.ExcecaoNegocio.responsavelServico.remover";
    public static final String REMOVER_PROFESSOR = "com.mycompany.x9_jsf.excecao.ExcecaoNegocio.professorServico.remover";
    public static final String REMOVER_TURMA = "com.mycompany.x9_jsf.excecao.ExcecaoNegocio.turmaServico.remover";
    
    public static final String OBJETO_EXISTENTE = "com.mycompany.x9_jsf.excecao.ExcecaoNegocio.objetoExistente";  
    public static final String ACESSO_NAO_AUTORIZADO = "com.mycompany.x9_jsf.excecao.ExcecaoNegocio.acesso.nao.autorizado";
    public static final String CREDENCIAIS_OMITIDAS = "com.mycompany.x9_jsf.excecao.ExcecaoNegocio.acesso.credenciais.omitidadas";
    public static final String LOGIN_INVALIDO = "com.mycompany.x9_jsf.excecao.ExcecaoNegocio.acesso.login.invalido";    
    
    public ExcecaoNegocio(String chave) {
        this.chave = chave;
    }  

    public String getChave() {
        return chave;
    }
    
    @Override
    public String getMessage() {
        MensagemExcecao mensagemExcecao = new MensagemExcecao(this);
        return mensagemExcecao.getMensagem();
    }
    
    public boolean isAutorizacao() {
        switch(chave) {
            case ACESSO_NAO_AUTORIZADO:
            case CREDENCIAIS_OMITIDAS:
            case LOGIN_INVALIDO:
                return true;
            default:
                return false;
        }
    }
}
