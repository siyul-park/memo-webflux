package com.ara.memo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpServerErrorException

object UserAlreadyExistException : HttpServerErrorException(HttpStatus.FORBIDDEN, "User is already exist.")