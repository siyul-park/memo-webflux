package com.ara.memo.integration

import com.ara.memo.view.user.UserView
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.BodyInserters

class UserResourceTests : IntegrationTests("/users") {
    @Test
    fun testGet() {
        val request = UserView(id = null, username = "test", password = "test")
        val response = create(request)

        assertNotNull(response.id)
        assertEquals(response.username, request.username)
        assertEquals(response.password, request.password)
    }

    fun create(user: UserView) = webClient.post()
        .uri(linkUri())
        .body(BodyInserters.fromValue(user))
        .exchange()
        .expectStatus().isOk
        .expectBody(UserView::class.java)
        .returnResult()
        .responseBody!!

}