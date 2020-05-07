package com.kottlingettingstart.main.controllers

import com.kottlingettingstart.main.documents.Employee
import com.kottlingettingstart.main.documents.Release
import com.kottlingettingstart.main.dtos.ReleaseDTO
import com.kottlingettingstart.main.enums.TypeEnum
import com.kottlingettingstart.main.repositories.ReleaseRepository
import com.kottlingettingstart.main.response.Response
import com.kottlingettingstart.main.services.impl.ImplEmployeeService
import com.kottlingettingstart.main.services.impl.ImplReleaseService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import javax.validation.Valid


@RestController
@RequestMapping("/api/release")
class ReleaseController(val releaseService: ImplReleaseService, val employeeService: ImplEmployeeService) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @PostMapping(value = [""])
    fun save(@Valid @RequestBody releaseDTO: ReleaseDTO, result: BindingResult): ResponseEntity<Response<ReleaseDTO>> {
        val response: Response<ReleaseDTO> = Response<ReleaseDTO>()
        validateEmployee(releaseDTO, result)

        if (result.hasErrors()) {
            for (err in result.allErrors) response.errors.add(err.defaultMessage!!)

            return ResponseEntity.badRequest().body(response)
        }

        var release: Release = convertDTOToRelease(releaseDTO, result)
        release = releaseService.save(release)
        response.data = convertReleaseDTO(release)
        return ResponseEntity.ok(response)
    }

    private fun validateEmployee(releaseDTO: ReleaseDTO, result: BindingResult) {
        if (releaseDTO.employeeId == null) {
            result.addError(ObjectError("Employee", "EmployeeId is required"))
            return
        }

        val employee: Employee? = employeeService.findById(releaseDTO.employeeId!!)
        if (employee == null) {
            result.addError(ObjectError("Employee", "Employee not found"))
        }
    }

    private fun convertDTOToRelease(releaseDTO: ReleaseDTO, result: BindingResult): Release {
        if (releaseDTO.id != null) {
            var release: Release? = releaseService.findById(releaseDTO.id!!)
            if (release == null) {
                result.addError(ObjectError("Release", "Release not found"))
            }
        }

        return Release(
                date = dateFormat.parse(releaseDTO.date),
                type = TypeEnum.valueOf(releaseDTO.type!!),
                employeeId = releaseDTO.employeeId!!,
                description = releaseDTO.description,
                location = releaseDTO.location,
                id = releaseDTO.id)
    }

    private fun convertReleaseDTO(release: Release): ReleaseDTO = ReleaseDTO(
            date = dateFormat.format(release.date),
            type = release.type.toString(),
            employeeId = release.employeeId!!,
            description = release.description,
            location = release.location,
            id = release.id)

    @GetMapping(value= ["/{releaseId}"])
    fun findById(@PathVariable("releaseId") releaseId: String): ResponseEntity<Response<ReleaseDTO>> {
        val response: Response<ReleaseDTO> = Response<ReleaseDTO>()
        val release: Release? = releaseService.findById(releaseId)
        if (release == null) {
            response.errors.add("Release not found to ID: $releaseId")
            return ResponseEntity.badRequest().body(release)
        }
        response.data = convertReleaseDTO(release)
        return ResponseEntity.ok(response)
    }

    @GetMapping(value= ["/employee/{employeeId}"])
    fun findAllByEmployeeId(
            @PathVariable("employeeId") employeeId: String,
            @RequestParam(value = "page", defaultValue = "0") page: Int,
            @RequestParam(value = "size", defaultValue = "15") size: Int,
            @RequestParam(value = "sort_column", defaultValue = "id") sort_column: String,
            @RequestParam(value = "sort_type", defaultValue = "DESC") sort_type: String): ResponseEntity<Response<Page<ReleaseDTO>>> {
        val response: Response<Page<ReleaseDTO>> = Response<Page<ReleaseDTO>>()
        val pageRequest: PageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.valueOf(sort_type),
                sort_column
        )
        val releases: Page<Release>? = releaseService.findAllByEmployeeId(employeeId, pageRequest)
        val releasesDTO: Page<ReleaseDTO> = releases?.map { release -> convertReleaseDTO(release) } ?: Page.empty()

        response.data = releasesDTO
        return ResponseEntity.ok(response)
    }

    @PutMapping(value= ["/{releaseId}"])
    fun update(@PathVariable("releaseId") releaseId: String, @Valid @RequestBody releaseDTO: ReleaseDTO, result: BindingResult): ResponseEntity<Response<ReleaseDTO>> {
        val response: Response<ReleaseDTO> = Response<ReleaseDTO>()
        validateEmployee(releaseDTO, result)
        releaseDTO.id = releaseId
        var release: Release = convertDTOToRelease(releaseDTO, result)
        if (result.hasErrors()) {
            for (err in result.allErrors) response.errors.add(err.defaultMessage!!)

            return ResponseEntity.badRequest().body(response)
        }
        release = releaseService.save(release)
        response.data = convertReleaseDTO(release)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping(value= ["/{releaseId}"])
    fun remove(@PathVariable("releaseId") releaseId: String): ResponseEntity<Response<String>> {
        val response: Response<ReleaseDTO> = Response<ReleaseDTO>()
        val release: Release? = releaseService.findById(releaseId)
        if (release == null) {
            response.errors.add("Release not found to ID: $releaseId")
            return ResponseEntity.badRequest().body(release)
        }
        releaseService.remove(releaseId)
        return ResponseEntity.ok(Response<String>())
    }
}