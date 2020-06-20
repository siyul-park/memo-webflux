package com.ara.memo.dao.user.exception

import com.ara.memo.exception.ForbiddenException

class UserAlreadyExistException(message: String? = "User is already exist.") : ForbiddenException(message)