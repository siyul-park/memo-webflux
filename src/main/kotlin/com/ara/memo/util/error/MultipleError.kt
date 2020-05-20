package com.ara.memo.util.error

class MultipleError(
    errors: Collection<SingleError>
) : Error, Collection<SingleError> by errors
