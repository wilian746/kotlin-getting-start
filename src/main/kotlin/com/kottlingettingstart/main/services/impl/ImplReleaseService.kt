package com.kottlingettingstart.main.services.impl

import com.kottlingettingstart.main.documents.Release
import com.kottlingettingstart.main.repositories.ReleaseRepository
import com.kottlingettingstart.main.services.IReleaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ImplReleaseService(val releaseRepository: ReleaseRepository) : IReleaseService {
    override fun findAllByEmployeeId(employeeId: String, pageRequest: PageRequest): Page<Release> = releaseRepository.findAllByEmployeeId(employeeId, pageRequest)

    override fun findById(id: String): Release? = releaseRepository.findByIdOrNull(id)

    override fun save(release: Release): Release = releaseRepository.save(release)

    override fun remove(id: String) = releaseRepository.deleteById(id)
}
