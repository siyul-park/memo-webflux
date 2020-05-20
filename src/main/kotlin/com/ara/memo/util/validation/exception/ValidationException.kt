package com.ara.memo.util.validation.exception

import com.ara.memo.util.error.Error
import com.ara.memo.util.error.ErrorProvider
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class ValidationException(private val error: Error)
    : ResponseStatusException(HttpStatus.BAD_REQUEST, "Validation error."),
    ErrorProvider {
    override fun get() = error

    override fun type() = error::class.java
}