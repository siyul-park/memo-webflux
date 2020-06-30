package com.ara.memo.integration

import com.ara.memo.dto.user.payload.UserCreatePayload
import com.ara.memo.dto.user.payload.UserResponsePayload
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.BodyInserters

class UserResourceTests : IntegrationTests("/users") {
    @Test
    fun testCreateSuccess() {
        val request = UserCreatePayload(username = "test", password = "test")
        val response = createSuccess(request)

        assertNotNull(response.id)
        assertEquals(response.username, request.username)
        assertNull(response.password)
    }

    private fun createSuccess(user: UserCreatePayload) = create(user)
        .expectStatus().isCreated
        .expectBody(UserResponsePayload::class.java)
        .returnResult()
        .responseBody!!

    private fun create(user: UserCreatePayload) = webClient.post()
        .uri(linkUri())
        .body(BodyInserters.fromValue(user))
        .exchange()
}