package com.ara.memo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class InternalServerException(reason: String?) : ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, reason)