package com.ara.memo.dto.user.payload

import com.ara.memo.dto.user.PasswordScope
import com.ara.memo.dto.user.UsernameScope
import com.ara.memo.entity.user.User
import com.fasterxml.jackson.annotation.JsonView
import java.util.*
import javax.validation.constraints.NotNull

class UserCreatePayload(
    @JsonView(UsernameScope::class)
    @field:NotNull
    val username: String = "",
    @JsonView(PasswordScope::class)
    @field:NotNull
    val password: String = ""
) {
    fun toUser() = User(username, password)
}