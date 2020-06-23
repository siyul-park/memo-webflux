package com.ara.memo.dto.user

import com.ara.memo.entity.user.User
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

    companion object {
        fun from(user: User) = with(user) {
            UserView(id, username, password)
        }
    }
}