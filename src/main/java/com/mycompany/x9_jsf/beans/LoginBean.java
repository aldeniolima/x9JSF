package com.mycompany.x9_jsf.beans;

import com.mycompany.x9_jsf.entidades.Administrador;
import com.mycompany.x9_jsf.entidades.Professor;
import com.mycompany.x9_jsf.entidades.Responsavel;
import com.mycompany.x9_jsf.entidades.Usuario;
import com.mycompany.x9_jsf.servico.UsuarioServico;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author joselima
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    @NotBlank
    private String login;
    @NotBlank
    private String senha;
    private FacesContext facesContext;

    @EJB
    private UsuarioServico servicoUsuario;

    /*
    private HttpServletRequest getRequest() {
        return (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }*/
    public String login() {
        try {
            facesContext = FacesContext.getCurrentInstance();

            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            request.login(login, senha);
            facesContext.getExternalContext().getSession(true);

            Usuario user = servicoUsuario.login(login, senha);
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", user);
        } catch (ServletException ex) {
            setLogin(null);
            adicionarMensagem("Senha ou usuário inválidos!");
            return "falha";
        }
        
        return "sucesso";
    }

    private void adicionarMensagem(String mensagem) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null);
        facesContext.addMessage(null, message);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
