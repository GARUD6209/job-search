package com.garud.job.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // GET /companies - Retrieve all companies
    @GetMapping
    public ResponseEntity<List<Company>> findAllCompanies() {
        List<Company> companies = companyService.findAllCompanies();
        if (companies != null && !companies.isEmpty()) {
            return new ResponseEntity<>(companies, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET /companies/{id} - Retrieve a company by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // POST /companies - Create a new company
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company companyRes = companyService.create(company);
        if (companyRes != null) {
            return new ResponseEntity<>(companyRes, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // PUT /companies/{id} - Update an existing company
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company updateCompany) {
        boolean isUpdated = companyService.updateCompany(id, updateCompany);
        if (isUpdated) {
            return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE /companies/{id} - Delete a company by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCompanyById(@PathVariable Long id) {
        boolean isDeleted = companyService.deleteCompanyById(id);
        if (isDeleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}