package com.kottlingettingstart.main.utils

import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.util.Assert

class PasswordUtilsTest {
    private val PASSWORD = "123456"
    private val bCryptEncoder = BCryptPasswordEncoder()

    @Test
    fun testGenerateHashPassowrd() {
        val hash = PasswordUtils().generateBcrypt(PASSWORD)
        Assert.isTrue(bCryptEncoder.matches(PASSWORD, hash), "Hash and passwords NOT match")
    }
}