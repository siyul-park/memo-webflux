package com.ara.memo.service.authentication

import java.time.Instant
import java.util.*

data class AuthenticationInfo(
    val subject: String,
    val audience: String,
    val issuedAt: Date = Date.from(Instant.now()),
    val activeAt: Date = issuedAt,
    val expiration: Date? = null
)