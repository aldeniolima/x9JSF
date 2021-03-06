package com.mycompany.x9_jsf.excecao;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.WebApplicationException;
import com.mycompany.x9_jsf.util.LeitorPropriedades;
import com.mycompany.x9_jsf.webservice.excecao.TradutorMensagemExcecao;

public class MensagemExcecao {

    protected Throwable excecao;
    protected static LeitorPropriedades leitor = new LeitorPropriedades(new String[]{"Exception.properties", "Mensagens.properties"});

    public MensagemExcecao(Throwable excecao) {
        this.excecao = excecao;
    }

    public String getMensagem() {
        StringBuilder mensagem = new StringBuilder();

        if (excecao instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) excecao).getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                if (mensagem.length() != 0) {
                    mensagem.append("; ");
                }

                mensagem.append(violation.getPropertyPath());
                mensagem.append(" ");
                mensagem.append(violation.getMessage());
            }

            mensagem
                    = new StringBuilder(String.format(
                                    leitor.get(excecao.getClass().getName()),
                                    mensagem.toString()));
        } else if (excecao instanceof ExcecaoNegocio) {
            mensagem.append(leitor.get(((ExcecaoNegocio) excecao).getChave()));
        } else if (excecao != null && leitor.get(excecao.getClass().getName()) != null) {
            mensagem.append(leitor.get(excecao.getClass().getName()));
        } else if (excecao instanceof WebApplicationException) {
            String traducao = new TradutorMensagemExcecao().getTraducao(excecao.getMessage());
            leitor.adicionar(excecao.getClass().getName(), traducao);            
            mensagem.append(traducao);
        } else {
            mensagem.append(leitor.get("java.lang.Exception"));
        }

        return mensagem.toString();
    }
}
