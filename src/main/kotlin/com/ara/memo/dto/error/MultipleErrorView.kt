package com.ara.memo.dto.error

import com.ara.memo.util.error.MultipleError

class MultipleErrorView(
    path: String,
    name: String,
    error: MultipleError? = null
) : ErrorView(path, name, error)