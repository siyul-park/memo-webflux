package com.ara.memo.contoller

import com.ara.memo.service.user.UserService
import com.ara.memo.view.user.UserView
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService
) {
    @PostMapping
    @JsonView(UserView.CreateResponseView::class)
    private fun create(
        @JsonView(UserView.CreateRequestView::class)
        @RequestBody
        userView: UserView
    ) = Mono.just(userView)
        .map { it.toUser() }
        .flatMap { service.create(it) }
        .map { UserView.from(it) }
}