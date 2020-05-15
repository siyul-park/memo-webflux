package com.ara.memo.service.user.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

object UserCantAuthorizeException : ResponseStatusException(HttpStatus.FORBIDDEN, "User can't authorize.")