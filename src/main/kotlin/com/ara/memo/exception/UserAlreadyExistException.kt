package com.ara.memo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

object UserAlreadyExistException : ResponseStatusException(HttpStatus.FORBIDDEN, "User is already exist.")