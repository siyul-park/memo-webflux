package com.ara.memo.contoller

import com.ara.memo.service.user.UserService
import com.ara.memo.view.user.UserView
import com.ara.memo.view.user.asUser
import com.ara.memo.view.user.from
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @JsonView(UserView.PublicScope::class)
    private fun create(
        @RequestBody
        @JsonView(UserView.ModifiableScope::class)
        @Validated(UserView.ModifiableScope::class)
        userView: Mono<UserView>
    ) = userView.map(UserView::asUser)
        .flatMap(service::signUp)
        .map(UserView::from)

    @GetMapping
    @ResponseBody
    @JsonView(UserView.PublicScope::class)
    private fun getAll() = service.findAll()
        .map(UserView::from)

    @GetMapping("/{id}")
    @ResponseBody
    @JsonView(UserView.PublicScope::class)
    private fun get(
        @PathVariable id: String
    ) = service.findById(id)
        .map(UserView::from)

    @PatchMapping("/{id}")
    @ResponseBody
    @JsonView(UserView.PublicScope::class)
    private fun update(
        @PathVariable id: String,
        @RequestBody
        @JsonView(UserView.ModifiableScope::class)
        userView: Mono<UserView>
    ) = userView
        .flatMap { newOne ->
            service.updateById(id) {
                newOne.username?.let { username = newOne.username }
                newOne.password?.let { password = newOne.password }
            }
        }
        .map(UserView::from)

    @DeleteMapping("/{id}")
    private fun delete(
        @PathVariable id: String
    ) = service.deleteByIdWhenExist(id)
}