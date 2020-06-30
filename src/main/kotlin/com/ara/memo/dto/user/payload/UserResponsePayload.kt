package com.ara.memo.dto.user.payload

import com.ara.memo.dto.user.IdScope
import com.ara.memo.dto.user.PasswordScope
import com.ara.memo.dto.user.UsernameScope
import com.ara.memo.entity.user.User
import com.fasterxml.jackson.annotation.JsonView

data class UserResponsePayload(
    @JsonView(IdScope::class)
    val id: String? = null,
    @JsonView(UsernameScope::class)
    val username: String,
    @JsonView(PasswordScope::class)
    val password: String
) {
    interface PublicProfile : IdScope, UsernameScope

    companion object {
        fun from(user: User) = with(user) {
            UserResponsePayload(id, username, password)
        }
    }
}