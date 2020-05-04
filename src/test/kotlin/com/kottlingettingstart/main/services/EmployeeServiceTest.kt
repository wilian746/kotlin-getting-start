package com.kottlingettingstart.main.services

import com.kottlingettingstart.main.documents.Employee
import com.kottlingettingstart.main.enums.ProfileEnum
import com.kottlingettingstart.main.repositories.EmployeeRepository
import com.kottlingettingstart.main.utils.PasswordUtils
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.util.Assert
import java.util.*

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    val employeeService: IEmployeeService? = null

    @MockBean
    private val employeeRepository: EmployeeRepository? = null

    private val CPF: String = "01819989046"
    private val EMAIL: String = "wilian@email.com"
    private val ID: String = "1"

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given(employeeRepository?.findOneByCpf(CPF)).willReturn(employee())
        BDDMockito.given(employeeRepository?.findOneByEmail(EMAIL)).willReturn(employee())
        BDDMockito.given(employeeRepository?.findById(ID)).willReturn(optionalEmployee(false))
        BDDMockito.given(employeeRepository?.findById(ID)).willReturn(optionalEmployee(true))
        BDDMockito.given(employeeRepository?.save(Mockito.any(Employee::class.java))).willReturn(employee())
    }

    @Test
    fun testSaveEmployee() {
        val found: Employee? = employeeService?.save(employee())
        Assert.notNull(found, "Employee to save is null")
    }

    @Test
    fun testFindOneByEmail() {
        val found: Employee? = employeeService?.findByEmail(EMAIL)
        Assert.notNull(found, "Employee to findByEmail is null")
    }

    @Test
    fun testFindOneByCPF() {
        val found: Employee? = employeeService?.findByCPF(CPF)
        Assert.notNull(found, "Employee to findByCPF is null")
    }

    @Test
    fun testFindOneByID() {
        val found: Employee? = employeeService?.findById(ID)
        Assert.notNull(found, "Employee to testFindOneByID is null")
    }

    private fun optionalEmployee(condition: Boolean): Optional<Employee> {
        if (condition) {
            return Optional.of(employee())
        }

        return Optional.ofNullable(employee())
    }

    private fun employee(): Employee = Employee("Wilian Gabriel", EMAIL, PasswordUtils().generateBcrypt("123456"), CPF, ProfileEnum.ROLE_USER, "1", 8.0, 8.0f, 1.0f, ID)
}