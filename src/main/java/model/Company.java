package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mkolbusz on 7/7/16.
 */
@Entity
@Table(name = "companies")
public class Company implements Serializable {
    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "company_id")
    int companyId;

    @Basic
    @Column(name = "name")
    String name;

    @Basic
    @Column(name = "auth_code")
    String authCode = "";



    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }


    @Override
    public boolean equals(Object obj) {
        Company company = (Company)obj;
        if(company.getName().equalsIgnoreCase(this.getName())){
            return true;
        }
        return false;
    }
}
