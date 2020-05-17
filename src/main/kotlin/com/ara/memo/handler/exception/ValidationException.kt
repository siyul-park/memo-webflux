package com.ara.memo.handler.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class ValidationException(message: String? = "Validation error.") : ResponseStatusException(HttpStatus.BAD_REQUEST, message)