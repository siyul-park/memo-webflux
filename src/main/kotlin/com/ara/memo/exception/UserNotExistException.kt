package com.ara.memo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpServerErrorException

object UserNotExistException : HttpServerErrorException(HttpStatus.NOT_FOUND, "User is not exist.")