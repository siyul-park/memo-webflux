package com.ara.memo.dto.authentication

import com.ara.memo.dto.user.PasswordScope
import com.ara.memo.dto.user.UsernameScope
import com.ara.memo.entity.user.User
import com.fasterxml.jackson.annotation.JsonView

data class UserAuthenticationPayload(
    @JsonView(UsernameScope::class)
    val username: String,
    @JsonView(PasswordScope::class)
    val password: String
)