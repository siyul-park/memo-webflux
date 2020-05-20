package com.ara.memo.dto.error

import com.ara.memo.util.error.SingleError

class SingleErrorView(
    path: String,
    name: String,
    error: SingleError? = null
) : ErrorView(path, name, error)