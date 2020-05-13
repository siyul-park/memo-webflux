package com.ara.memo.dto.user

import com.fasterxml.jackson.annotation.JsonView
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

class UserRequest(
    @Null(groups = [IdNull::class])
    id: String? = null,
    @JsonView(UsernameScope::class)
    @NotNull(groups = [UsernameNotNull::class])
    username: String? = null,
    @JsonView(PasswordScope::class)
    @NotNull(groups = [PasswordNotNull::class])
    password: String? = null
) : UserDto(id, username, password) {
    interface Create : IdNull, UsernameScope, UsernameNotNull, PasswordScope, PasswordNotNull
    interface Modify : IdNull, UsernameScope, PasswordScope

    interface UsernameScope
    interface PasswordScope

    interface IdNull
    interface UsernameNotNull
    interface PasswordNotNull
}