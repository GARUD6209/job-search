package com.garud.job.company;

import java.util.List;

public interface CompanyService {
    List<Company> findAllCompanies();

    Company getCompanyById(Long id) ;

    Company create(Company company);

    boolean updateCompany(Long id, Company updateCompany);

    boolean deleteCompanyById(Long id);
}
