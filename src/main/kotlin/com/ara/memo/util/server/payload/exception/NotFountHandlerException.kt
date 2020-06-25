package com.ara.memo.util.server.payload.exception

import com.ara.memo.exception.InternalServerException
import com.ara.memo.exception.UnauthorizedException

object NotFountHandlerException : InternalServerException("Authentication failed.")