package com.ara.memo.util.validation.exception

import com.ara.memo.util.error.Error
import com.ara.memo.util.error.WrappedErrorProvider

class ValidationErrorProvider(override val error: Error) : ValidationException("Validation error."), WrappedErrorProvider<Error>
