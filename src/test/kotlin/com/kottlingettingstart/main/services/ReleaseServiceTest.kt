package com.kottlingettingstart.main.services

import com.kottlingettingstart.main.documents.Release
import com.kottlingettingstart.main.enums.TypeEnum
import com.kottlingettingstart.main.repositories.ReleaseRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.util.Assert
import java.util.*
import kotlin.collections.ArrayList

@SpringBootTest
class ReleaseServiceTest {

    @Autowired
    val releaseService: IReleaseService? = null

    @MockBean
    private val releaseRepository: ReleaseRepository? = null

    private var ID: String = "1"

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        ID += "1"
        val pageReq = PageRequest.of(0, 10)
        val pageRes = PageImpl(ArrayList<Release>())
        BDDMockito.given(releaseRepository?.findAllByEmployeeId(ID, pageReq)).willReturn(pageRes)
        BDDMockito.given(releaseRepository?.findById(ID)).willReturn(optionalRelease(false))
        BDDMockito.given(releaseRepository?.findById(ID)).willReturn(optionalRelease(true))
        BDDMockito.given(releaseRepository?.save(Mockito.any(Release::class.java))).willReturn(release())
    }

    @Test
    fun testSaveEmployee() {
        val save: Release? = releaseService?.save(release())
        Assert.notNull(save, "Release to save is null")
    }

    @Test
    fun testFindById() {
        val findById: Release? = releaseService?.findById(ID)
        Assert.notNull(findById, "Release to findById is null")
    }

    @Test
    fun testFindAllByEmployeeId() {
        val pageReq = PageRequest.of(0, 10)
        val findAllByEmployeeId: Page<Release>? = releaseService?.findAllByEmployeeId(ID, pageReq)
        Assert.notNull(findAllByEmployeeId, "Release to findAllByEmployeeId is null")
    }

    @Test
    fun testRemove() {
        assertDoesNotThrow {
            releaseService?.remove(ID)
        }
    }

    private fun optionalRelease(condition: Boolean): Optional<Release> {
        if (condition) {
            return Optional.of(release())
        }

        return Optional.ofNullable(release())
    }

    private fun release(): Release = Release(Date(), TypeEnum.WORK_START, "1", "Start work", "location 1", ID)
}