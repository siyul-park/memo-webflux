package com.ara.memo.service.user.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

object UserNotExistException : ResponseStatusException(HttpStatus.NOT_FOUND, "User is not exist.")