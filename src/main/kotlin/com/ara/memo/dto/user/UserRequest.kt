package com.ara.memo.dto.user

import com.ara.memo.entity.user.User
import com.fasterxml.jackson.annotation.JsonView
import javax.validation.constraints.NotNull

data class UserRequest(
    @JsonView(UsernameScope::class)
    @NotNull(groups = [UsernameNotNull::class])
    val username: String? = null,
    @JsonView(PasswordScope::class)
    @NotNull(groups = [PasswordNotNull::class])
    val password: String? = null
) {
    interface Create : UsernameScope, UsernameNotNull, PasswordScope, PasswordNotNull
    interface Modify : UsernameScope, PasswordScope

    interface UsernameScope
    interface PasswordScope

    interface UsernameNotNull
    interface PasswordNotNull

    fun toUser() = User(username!!, password!!)
}