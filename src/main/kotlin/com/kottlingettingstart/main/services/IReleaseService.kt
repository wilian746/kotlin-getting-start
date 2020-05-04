package com.kottlingettingstart.main.services

import com.kottlingettingstart.main.documents.Release
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface IReleaseService {
    fun findAllByEmployeeId(employeeId: String, pageRequest: PageRequest): Page<Release>
    fun findById(id: String): Release?
    fun save(release: Release): Release
    fun remove(id: String)
}
