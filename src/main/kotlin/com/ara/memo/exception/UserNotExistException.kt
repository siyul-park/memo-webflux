package com.ara.memo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

object UserNotExistException : ResponseStatusException(HttpStatus.NOT_FOUND, "User is not exist.")