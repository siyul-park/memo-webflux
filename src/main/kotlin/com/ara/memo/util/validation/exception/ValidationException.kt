package com.ara.memo.util.validation.exception

import com.ara.memo.exception.BadRequestException

open class ValidationException(reason: String?) : BadRequestException(reason)