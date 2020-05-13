package com.ara.memo.contoller

import com.ara.memo.dto.user.UserRequest
import com.ara.memo.dto.user.UserView
import com.ara.memo.service.user.UserService
import com.fasterxml.jackson.annotation.JsonView
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@Api(value = "User Controller")
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

    @GetMapping("/{user-id}")
    @ResponseBody
    @JsonView(UserView.PublicProfile::class)
    private fun get(
        @PathVariable(name = "user-id") id: String
    ) = service.findById(id)
        .map { UserView.from(it) }

    @PatchMapping("/{user-id}")
    @ResponseBody
    @JsonView(UserView.PublicProfile::class)
    private fun update(
        @PathVariable(name = "user-id") id: String,
        @RequestBody
        @JsonView(UserRequest.Modify::class)
        @Validated(UserRequest.Modify::class)
        request: UserRequest
    ) = service.updateById(id) {
            request.username?.let { username = it }
            request.password?.let { password = it }
        }.map { UserView.from(it) }

    @DeleteMapping("/{user-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun delete(
        @PathVariable(name = "user-id") id: String
    ) = service.deleteByIdWhenExist(id)
}