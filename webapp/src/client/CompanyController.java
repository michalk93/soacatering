package client;

import common.CompanyService;
import model.Company;

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
public class CompanyController {
    Company company;

    List<Company> companyList;

    @EJB(lookup = "java:jboss/exported/clientAccount/companyService!common.CompanyService")
    CompanyService companyService;


    @PostConstruct
    public void init(){
        company = new Company();
        companyList = companyService.getAll();
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public void save(){
        companyService.save(company);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Firma zostałą dodana", company.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
