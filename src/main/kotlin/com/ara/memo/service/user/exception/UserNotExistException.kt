package com.ara.memo.service.user.exception

import com.ara.memo.exception.NotFoundException

object UserNotExistException : NotFoundException("User is not exist.")