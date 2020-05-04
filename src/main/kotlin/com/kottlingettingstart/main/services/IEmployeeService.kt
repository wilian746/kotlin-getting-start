package com.kottlingettingstart.main.services

import com.kottlingettingstart.main.documents.Employee

interface IEmployeeService {
    fun save(employee: Employee): Employee
    fun findByCPF(cpf: String): Employee?
    fun findByEmail(email: String): Employee?
    fun findById(id: String): Employee?
}
