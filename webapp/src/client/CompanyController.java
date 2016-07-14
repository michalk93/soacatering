package client;

import common.CompanyService;
import model.Company;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 * Created by mkolbusz on 7/7/16.
 */
@ManagedBean
public class CompanyController {
    Company company;

    @EJB(lookup = "java:jboss/exported/clientAccount/companyService!common.CompanyService")
    CompanyService companyService;


    @PostConstruct
    public void init(){
        company = new Company();
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void save(){
        companyService.save(company);
    }
}
