package com.kottlingettingstart.main.repositories

import com.kottlingettingstart.main.documents.Employee
import org.springframework.data.mongodb.repository.MongoRepository

interface EmployeeRepository : MongoRepository<Employee, String> {
    fun findOneByCpf(cpf: String): Employee
    fun findOneByEmail(email: String): Employee
}
