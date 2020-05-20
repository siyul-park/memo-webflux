package com.ara.memo.util.error

object Errors {
    fun of(message: String? = null) = SingleError(message)
    fun of(errors: Collection<Error>) = MultipleError(errors)
}