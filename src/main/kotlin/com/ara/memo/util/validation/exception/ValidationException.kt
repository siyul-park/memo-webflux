package com.ara.memo.util.validation.exception

import com.ara.memo.util.error.Error
import com.ara.memo.util.error.WrappedErrorProvider
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class ValidationException(override val error: Error)
    : ResponseStatusException(HttpStatus.BAD_REQUEST, "Validation error."), WrappedErrorProvider
