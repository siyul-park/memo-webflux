package com.ara.meno.integration

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HelloResourceTests : IntegrationTests("/hello") {
    @Test
    fun testGet() {
        val result = get()

        assertEquals(result.responseBody, "Hello, Spring!")
    }

    fun get() = webClient.get()
        .uri(linkUri(""))
        .exchange()
        .expectStatus().isOk
        .expectBody(String::class.java)
        .returnResult()
}