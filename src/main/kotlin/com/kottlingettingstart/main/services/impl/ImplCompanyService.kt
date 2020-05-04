package com.kottlingettingstart.main.services.impl

import com.kottlingettingstart.main.documents.Company
import com.kottlingettingstart.main.repositories.CompanyRepository
import com.kottlingettingstart.main.services.ICompanyService
import org.springframework.stereotype.Service

@Service
class ImplCompanyService(val companyRepository: CompanyRepository) : ICompanyService {
    override fun findByCNPJ(cnpj: String): Company? = companyRepository.findOneByCnpj(cnpj)
    override fun save(company: Company): Company = companyRepository.save(company)
}