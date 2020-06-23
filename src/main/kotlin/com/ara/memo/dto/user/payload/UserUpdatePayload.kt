package com.ara.memo.dto.user.payload

import com.ara.memo.dto.user.PasswordScope
import com.ara.memo.dto.user.UsernameScope
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonView
import java.util.*
import javax.validation.constraints.NotEmpty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserUpdatePayload(
    @JsonView(UsernameScope::class)
    @NotEmpty
    val username: Optional<String>? = null,
    @JsonView(PasswordScope::class)
    @NotEmpty
    val password: Optional<String>? = null
)