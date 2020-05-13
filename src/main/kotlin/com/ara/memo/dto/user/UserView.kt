package com.ara.memo.dto.user

import com.ara.memo.entity.User
import com.ara.memo.dto.View
import com.fasterxml.jackson.annotation.JsonView
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

class UserView(
    @JsonView(IdScope::class)
    id: String? = null,
    @JsonView(UsernameScope::class)
    username: String? = null,
    @JsonView(PasswordScope::class)
    password: String? = null
) : UserDto(id, username, password), View {
    interface PublicProfile : IdScope, UsernameScope

    interface IdScope
    interface UsernameScope
    interface PasswordScope

    companion object {
        fun from(user: User) = with(user) { UserView(id, username, password) }
    }
}