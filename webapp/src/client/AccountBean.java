package client;


import common.ClientAccount;
import exceptions.UserSessionExistsException;
import model.User;

import javax.annotation.PostConstruct;
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
    public User user;

    @EJB(lookup = "java:jboss/exported/clientAccount/ClientAccountImplementation!common.ClientAccount")
    ClientAccount clientAccount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @PostConstruct
    public void init() {
        user = new User();
    }

    public void register(){
        clientAccount.register(user);
    }

    public String login(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        try {
            if(request.getUserPrincipal() != null){
                request.logout();
            }
            request.login(user.getEmail(), user.getPassword());
            user = clientAccount.login(user);
            context.getExternalContext().getSessionMap().put("user", user);
            response.sendRedirect("index.xhtml");
            return null;
        }catch (UserSessionExistsException e) {
            context.addMessage(null, new FacesMessage("Użytkownik już zalogowany."));
            return null;
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Użytkownik już zalogowany."));
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.addMessage(null, new FacesMessage("Nieprawidłowe dane logowania. Spróbuj ponownie."));
        return null;
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User user = (User) session.getAttribute("user");

        if(clientAccount.logout(user)){
            try {
                request.logout();
                session.invalidate();
            } catch (ServletException e) {
                e.printStackTrace();
            }

        }
        return "/public/index?faces-redirect=true";
    }

}
