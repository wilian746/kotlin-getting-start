package com.kottlingettingstart.main.services.impl

import com.kottlingettingstart.main.documents.Employee
import com.kottlingettingstart.main.repositories.EmployeeRepository
import com.kottlingettingstart.main.services.ICompanyService
import com.kottlingettingstart.main.services.IEmployeeService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ImplEmployeeService(val employeeRepository: EmployeeRepository) : IEmployeeService {
    override fun save(employee: Employee): Employee = employeeRepository.save(employee)

    override fun findByCPF(cpf: String): Employee? = employeeRepository.findOneByCpf(cpf)

    override fun findByEmail(email: String): Employee? = employeeRepository.findOneByEmail(email)

    override fun findById(id: String): Employee? = employeeRepository.findByIdOrNull(id)
}