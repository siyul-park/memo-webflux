package com.ara.memo.dto.token

data class TokenView(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int,
    val refreshToken: String? = null
)