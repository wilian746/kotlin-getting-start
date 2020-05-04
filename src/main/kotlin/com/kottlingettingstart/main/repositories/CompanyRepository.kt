package com.kottlingettingstart.main.repositories

import com.kottlingettingstart.main.documents.Company
import org.springframework.data.mongodb.repository.MongoRepository

interface CompanyRepository : MongoRepository<Company, String> {
    fun findOneByCnpj(cnpj: String): Company
}