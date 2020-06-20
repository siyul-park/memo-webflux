package com.ara.memo.service.user.exception

import com.ara.memo.exception.ForbiddenException

object UserCantAuthorizeException : ForbiddenException("User can't authorize.")