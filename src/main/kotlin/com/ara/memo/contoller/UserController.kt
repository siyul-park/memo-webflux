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
@ResponseBody
class UserController(
    private val service: UserService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(UserView.PublicProfile::class)
    private fun create(
        @RequestBody
        @JsonView(UserRequest.Create::class)
        @Validated(UserRequest.Create::class)
        request: Mono<UserRequest>
    ) = request.map(UserRequest::toUser)
        .flatMap(service::create)
        .map { UserView.from(it) }

    @GetMapping
    @JsonView(UserView.PublicProfile::class)
    private fun getAll()
        = service.findAll()
        .map { UserView.from(it) }

    @GetMapping("/{userId}")
    @JsonView(UserView.PublicProfile::class)
    private fun get(
        @PathVariable userId: String
    ) = service.findById(userId)
        .map { UserView.from(it) }

    @PatchMapping("/{userId}")
    @JsonView(UserView.PublicProfile::class)
    private fun update(
        @PathVariable userId: String,
        @RequestBody
        @JsonView(UserRequest.Modify::class)
        @Validated(UserRequest.Modify::class)
        request: UserRequest
    ) = service.updateById(userId) {
            request.username?.let { username = it }
            request.password?.let { password = it }
        }.map { UserView.from(it) }

    @DeleteMapping("/userId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun delete(
        @PathVariable userId: String
    ) = service.deleteByIdWhenExist(userId)
}