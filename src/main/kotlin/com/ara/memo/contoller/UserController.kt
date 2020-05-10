package com.ara.memo.contoller

import com.ara.memo.exception.UserNotExistException
import com.ara.memo.service.user.UserService
import com.ara.memo.view.user.UserView
import com.ara.memo.view.user.UserViewConverter
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService
) {
    @PostMapping
    @JsonView(UserView.PublicView::class)
    private fun create(
        @RequestBody
        @JsonView(UserView.ModifiableView::class)
        userView: Mono<UserView>
    ) = userView.map(UserViewConverter::toEntity)
        .flatMap(service::signUp)
        .map(UserViewConverter::toView)

    @GetMapping
    @JsonView(UserView.PublicView::class)
    private fun getAll() = service.findAll()
        .map(UserViewConverter::toView)

    @GetMapping("/{id}")
    @JsonView(UserView.PublicView::class)
    private fun get(
        @PathVariable id: String
    ) = service.findById(id)
        .map(UserViewConverter::toView)

    @PatchMapping("/{id}")
    @JsonView(UserView.PublicView::class)
    private fun update(
        @PathVariable id: String,
        @RequestBody
        @JsonView(UserView.ModifiableView::class)
        userView: Mono<UserView>
    )  = userView.map(UserViewConverter::toEntity)
        .flatMap { newOne ->
            service.updateById(id) {
                username = newOne.username
                password = newOne.password
            }
        }
        .map(UserViewConverter::toView)
}