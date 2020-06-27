package com.ara.memo.dto.user.payload

import com.ara.memo.dto.user.PasswordScope
import com.ara.memo.dto.user.UsernameScope
import com.ara.memo.util.validation.annotation.NullableNotEmpty
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonView
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserUpdatePayload(
    @JsonView(UsernameScope::class)
    @NullableNotEmpty
    val username: Optional<String>? = null,
    @JsonView(PasswordScope::class)
    @NullableNotEmpty
    val password: Optional<String>? = null
)