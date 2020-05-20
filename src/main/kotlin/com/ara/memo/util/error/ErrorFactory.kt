package com.ara.memo.util.error

interface ErrorFactory {
    fun of(message: String? = null): SingleError
    fun of(errors: Collection<Error>): MultipleError
}