package com.rohan.companyms.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rohan.companyms.company.Company;
import com.rohan.companyms.company.CompanyRepository;
import com.rohan.companyms.company.CompanyService;


// import com.rohan.jobApp.job.Job;

@Service
public class CompanyServiceImpl implements CompanyService{
    private CompanyRepository companyRepository;
    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            
            companyRepository.save(companyToUpdate);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }
    
    @Override
    public boolean deleteCompany(Long id){
         Optional<Company>companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            // Company companyToDelete = companyOptional.get();
            companyRepository.deleteById(id);
            return true;

        }else{
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
