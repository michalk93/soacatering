package common;

import model.Company;

import javax.ejb.Remote;

/**
 * Created by mkolbusz on 7/7/16.
 */
@Remote
public interface CompanyService {
    boolean save(Company company);
}
