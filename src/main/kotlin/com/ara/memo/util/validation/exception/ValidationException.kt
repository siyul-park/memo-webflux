package com.ara.memo.util.validation.exception

import com.ara.memo.exception.BadRequestException
import com.ara.memo.util.error.Error
import com.ara.memo.util.error.WrappedErrorProvider

class ValidationException(override val error: Error) : BadRequestException("Validation error."), WrappedErrorProvider<Error>
