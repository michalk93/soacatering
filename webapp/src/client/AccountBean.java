package client;


import common.ClientAccount;
import model.Client;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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
        if(clientAccount.login(client)){
            context.getExternalContext().getSessionMap().put("user", client);
            return "index?faces-redirect=true";
        }
        context.addMessage(null, new FacesMessage("Nieprawidłowe dane logowania. Spróbuj ponownie."));
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

}
