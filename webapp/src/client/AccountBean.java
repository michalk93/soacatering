package client;


import common.ClientAccount;
import model.Client;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by mkolbusz on 6/5/16.
 */
@ManagedBean
@RequestScoped
public class AccountBean {
    public Client client = new Client();

    @EJB(lookup = "java:jboss/exported/clientAccount/ClientAccountImplementation!common.ClientAccount")
    ClientAccount clientAccount;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public void register(){
        clientAccount.register(client);
    }

    public String login(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        try {
            request.login(client.getEmail(), client.getPassword());
            context.getExternalContext().getSessionMap().put("user", client);
            clientAccount.login(client);
            response.sendRedirect("index.xhtml");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.addMessage(null, new FacesMessage("Nieprawidłowe dane logowania. Spróbuj ponownie."));
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/public/index?faces-redirect=true";
    }

}
