package com.ara.memo.dto.error

import com.ara.memo.entity.error.Error

class ErrorView(
    val path: String,
    val error: Error
)