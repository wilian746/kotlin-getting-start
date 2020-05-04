package com.kottlingettingstart.main.documents


import com.kottlingettingstart.main.enums.TypeEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Release (
        val date: Date,
        val type: TypeEnum,
        val employeeId: String,
        val description: String? = "",
        val location: String? = "",
        @Id val id: String? = null
)
