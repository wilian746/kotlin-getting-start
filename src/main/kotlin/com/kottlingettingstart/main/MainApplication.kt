package com.kottlingettingstart.main

import com.kottlingettingstart.main.documents.Company
import com.kottlingettingstart.main.documents.Employee
import com.kottlingettingstart.main.enums.ProfileEnum
import com.kottlingettingstart.main.repositories.CompanyRepository
import com.kottlingettingstart.main.repositories.EmployeeRepository
import com.kottlingettingstart.main.repositories.ReleaseRepository
import com.kottlingettingstart.main.utils.PasswordUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration

@SpringBootApplication
class MainApplication(val companyRepository: CompanyRepository, val employeeRepository: EmployeeRepository, val releaseRepository: ReleaseRepository): CommandLineRunner {
	override fun run(vararg args: String?) {
		companyRepository.deleteAll()
		employeeRepository.deleteAll()
		releaseRepository.deleteAll()

		var company = Company("WGS", "84610383000100")
		company = companyRepository.save(company)
		var admin = Employee("Jose Vargas", "jose@email.com", PasswordUtils().generateBcrypt("123456"), "12356785043", ProfileEnum.ROLE_ADMIN, company.id!!)
		admin = employeeRepository.save(admin)
		var employee = Employee("Wilian Gabriel", "wilian@email.com", PasswordUtils().generateBcrypt("123456"), "01819989046", ProfileEnum.ROLE_USER, company.id!!)
		employee = employeeRepository.save(employee)

		System.out.println("Company ID: " + company.id)
		System.out.println("Admin ID: " + admin.id)
		System.out.println("Employee ID: " + employee.id)
	}
}

fun main(args: Array<String>) {
	SpringApplication.run(MainApplication::class.java, *args)
}
