package com.ara.memo.integration

import com.ara.memo.dto.error.MultipleErrorView
import com.ara.memo.dto.user.view.UserView
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.BodyInserters

class UserResourceTests : IntegrationTests("/users") {
    @Test
    fun testCreateFail() {
        val request = UserView(username = null, password = "test")
        val response = createFail(request)

        assertNotNull(response.error)
    }

    @Test
    fun testCreateSuccess() {
        val request = UserView(username = "test", password = "test")
        val response = createSuccess(request)

        assertNotNull(response.id)
        assertEquals(response.username, request.username)
        assertNull(response.password)
    }

    private fun createFail(user: UserView) = create(user)
        .expectStatus().is4xxClientError
        .expectBody(MultipleErrorView::class.java)
        .returnResult()
        .responseBody!!

    private fun createSuccess(user: UserView) = create(user)
        .expectStatus().isCreated
        .expectBody(UserView::class.java)
        .returnResult()
        .responseBody!!

    private fun create(user: UserView) = webClient.post()
        .uri(linkUri())
        .body(BodyInserters.fromValue(user))
        .exchange()
}