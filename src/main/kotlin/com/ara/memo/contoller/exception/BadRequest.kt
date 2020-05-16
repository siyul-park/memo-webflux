package com.ara.memo.contoller.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

object BadRequest : ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request.")