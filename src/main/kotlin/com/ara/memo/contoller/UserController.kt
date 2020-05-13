package com.ara.memo.contoller

import com.ara.memo.dto.user.UserRequest
import com.ara.memo.dto.user.UserView
import com.ara.memo.service.user.UserService
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
    @JsonView(UserView.PublicProfile::class)
    private fun create(
        @RequestBody
        @JsonView(UserRequest.Create::class)
        @Validated(UserRequest.Create::class)
        request: Mono<UserRequest>
    ) = request.map(UserRequest::toEntity)
        .flatMap(service::signUp)
        .map { UserView.from(it) }

    @GetMapping
    @ResponseBody
    @JsonView(UserView.PublicProfile::class)
    private fun getAll()
        = service.findAll()
        .map { UserView.from(it) }

    @GetMapping("/{id}")
    @ResponseBody
    @JsonView(UserView.PublicProfile::class)
    private fun get(
        @PathVariable id: String
    ) = service.findById(id)
        .map { UserView.from(it) }

    @PatchMapping("/{id}")
    @ResponseBody
    @JsonView(UserView.PublicProfile::class)
    private fun update(
        @PathVariable id: String,
        @RequestBody
        @JsonView(UserRequest.Modify::class)
        @Validated(UserRequest.Modify::class)
        request: UserRequest
    ) = service.updateById(id) {
            request.username?.let { username = it }
            request.password?.let { password = it }
        }.map { UserView.from(it) }

    @DeleteMapping("/{id}")
    private fun delete(
        @PathVariable id: String
    ) = service.deleteByIdWhenExist(id)
}