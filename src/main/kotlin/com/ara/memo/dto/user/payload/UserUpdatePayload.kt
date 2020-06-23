package com.ara.memo.dto.user.payload

import com.ara.memo.dto.user.PasswordScope
import com.ara.memo.dto.user.UsernameScope
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonView
import java.util.*
import javax.validation.constraints.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserUpdatePayload(
    @JsonView(UsernameScope::class)
    val username: Optional<@NotNull String>? = null,
    @JsonView(PasswordScope::class)
    val password: Optional<@NotNull String>? = null
)