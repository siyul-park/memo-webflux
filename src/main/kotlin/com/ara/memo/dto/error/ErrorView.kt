package com.ara.memo.dto.error

import com.ara.memo.dto.View

class ErrorView(
    val error: String,
    val path: String,
    val message: String
) : View