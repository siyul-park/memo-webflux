package com.ara.memo.integration

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod

class HelloResourceTests : IntegrationTests("/hello") {
    @Test
    fun testGet() {
        val result = get()

        assertEquals(result.responseBody, "Hello, Spring!")
    }

    fun get() = getResponseSpec(HttpMethod.GET)
        .expectStatus().isOk
        .expectBody(String::class.java)
        .returnResult()
}