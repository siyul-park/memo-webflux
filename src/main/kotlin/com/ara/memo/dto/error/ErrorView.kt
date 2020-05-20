package com.ara.memo.dto.error

import com.ara.memo.util.error.Error

class ErrorView(
    val path: String,
    val name: String,
    val error: Error? = null
)