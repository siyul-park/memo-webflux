package com.ara.memo.dto.user

import com.ara.memo.entity.User
import com.fasterxml.jackson.annotation.JsonView

data class UserView(
    @JsonView(IdScope::class)
    val id: String? = null,
    @JsonView(UsernameScope::class)
    val username: String? = null,
    @JsonView(PasswordScope::class)
    val password: String? = null
) {
    interface PublicProfile : IdScope, UsernameScope

    interface IdScope
    interface UsernameScope
    interface PasswordScope

    companion object {
        fun from(user: User) = with(user) { UserView(id, username, password) }
    }
}