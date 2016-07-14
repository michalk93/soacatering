package client;

import common.ClientAccount;
import model.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by mkolbusz on 7/7/16.
 */
@ManagedBean
public class UserSetttingsController {
    User user;
    String newPassword;
    String newPasswordConfirmation;

    String authCode;
    List companiesList;


    @EJB(lookup = "java:jboss/exported/clientAccount/ClientAccountImplementation!common.ClientAccount")
    ClientAccount clientAccount;

    @PostConstruct
    public void init(){
        companiesList = clientAccount.getAllCompanies();
        user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");;
    }


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirmation() {
        return newPasswordConfirmation;
    }

    public void setNewPasswordConfirmation(String newPasswordConfirmation) {
        this.newPasswordConfirmation = newPasswordConfirmation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List getCompaniesList() {
        return companiesList;
    }

    public void setCompaniesList(List companiesList) {
        this.companiesList = companiesList;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void changePassword(){
        if(!newPassword.equals(newPasswordConfirmation)){
            FacesMessage msg = new FacesMessage("Błąd", "Podane hasła nie zgadzają się");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if(clientAccount.changePassword(newPassword, user)){
            FacesMessage msg = new FacesMessage("Sukces", "Hasło zostało zmienione pomyślnie");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

        FacesMessage msg = new FacesMessage("Błąd", "Wystąpił błąd podczas zmiany hasła");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void registerCompany(){
        if(clientAccount.registerCompany(user, this.getAuthCode())){
            FacesMessage msg = new FacesMessage("Sukces", "Potrącenia z wypłat zostały włączone");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        FacesMessage msg = new FacesMessage("Błąd", "Potrącenia z wypłat nie zostały włączone");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


}
