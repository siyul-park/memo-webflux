package com.ara.memo.util.error

class MultipleError(
    errors: Collection<Error>
) : Error, Collection<Error> by errors
