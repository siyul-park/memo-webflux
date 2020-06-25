package com.ara.memo.service.authentication.exception

import com.ara.memo.exception.UnauthorizedException

object AuthenticationException : UnauthorizedException("Authentication failed.")