package com.ara.memo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class ForbiddenException(reason: String?) : ResponseStatusException(HttpStatus.FORBIDDEN, reason)