package com.kottlingettingstart.main.services

import com.kottlingettingstart.main.documents.Company
import com.kottlingettingstart.main.repositories.CompanyRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.util.Assert

@SpringBootTest
class CompanyServiceTest {    @Autowired
val companyService: ICompanyService? = null

    @MockBean
    private val companyRepository: CompanyRepository? = null

    private val CNPJ = "84610383000100"

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given(companyRepository?.findOneByCnpj(CNPJ)).willReturn(company())
        BDDMockito.given(companyRepository?.save(company())).willReturn(company())
    }

    @Test
    fun testFindCompanyByCNPJ() {
        val found: Company? = companyService?.findByCNPJ(CNPJ)
        Assert.notNull(found, "Company is null")
    }

    @Test
    fun testSaveCompany() {
        val found: Company? = companyService?.save(company())
        Assert.notNull(found, "Company is null")
    }

    private fun company(): Company = Company("Social reason", CNPJ)
}
