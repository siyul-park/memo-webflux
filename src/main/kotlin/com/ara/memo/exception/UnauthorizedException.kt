package com.ara.memo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class UnauthorizedException(reason: String?) : ResponseStatusException(HttpStatus.UNAUTHORIZED, reason)