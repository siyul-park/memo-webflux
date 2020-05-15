package com.ara.memo.dao.user.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class UserAlreadyExistException(message: String? = "User is already exist.") : ResponseStatusException(HttpStatus.FORBIDDEN, message)