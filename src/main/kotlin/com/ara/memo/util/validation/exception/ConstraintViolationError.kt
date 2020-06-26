package com.ara.memo.util.validation.exception

import com.ara.memo.exception.BadRequestException
import com.ara.memo.util.error.MultipleError
import com.ara.memo.util.error.WrappedErrorProvider

class ConstraintViolationError(override val error: MultipleError) : BadRequestException("Validation fail."), WrappedErrorProvider<MultipleError>