package com.garud.job.company.impl;

import com.garud.job.company.Company;
import com.garud.job.company.CompanyRepository;
import com.garud.job.company.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAllCompanies() {


        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {

        try {
            Optional<Company> companyOptional = companyRepository.findById(id);
            if (companyOptional.isPresent()) {
                Company company = companyOptional.get();

                return company;
            }
        } catch (Exception e) {
            return new Company();
        }
        return null;
    }

    @Override
    public Company create(Company company) {

        return companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company updateCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setName(updateCompany.getName());
            company.setJobs(updateCompany.getJobs());
            company.setDescription(updateCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyById(Long id) {

        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
