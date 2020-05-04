package com.kottlingettingstart.main.repositories

import com.kottlingettingstart.main.documents.Release
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.repository.MongoRepository

interface ReleaseRepository : MongoRepository<Release, String> {
    fun findAllByEmployeeId(employeeId: String, pageable: PageRequest): Page<Release>
}
