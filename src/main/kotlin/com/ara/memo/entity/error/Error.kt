package com.ara.memo.entity.error

class Error(
    val message: String,
    val cause: Throwable? = null
)
