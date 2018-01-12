
package com.mycompany.x9_jsf.excecao;

import javax.ejb.ApplicationException;


@ApplicationException(rollback = true)
public class ExcecaoSistema extends RuntimeException {
    public ExcecaoSistema(Throwable causa) {
        super(causa);
    }
    
    @Override
    public String getMessage() {
        MensagemExcecao mensagemExcecao = new MensagemExcecao(this);
        return mensagemExcecao.getMensagem();
    }    
}
