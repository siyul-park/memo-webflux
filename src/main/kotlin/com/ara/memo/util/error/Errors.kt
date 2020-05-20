package com.ara.memo.util.error

import com.ara.memo.util.cache.LocalCache
import com.ara.memo.util.cache.getOrPut

object Errors {
    private val singleErrorCache = LocalCache<String, SingleError>()
    private val multipleErrorCache = LocalCache<Collection<Error>, MultipleError>()

    fun of(message: String? = null) = singleErrorCache.getOrPut(message) { SingleError(message) }
    fun of(errors: Collection<Error>) = multipleErrorCache.getOrPut(errors) { MultipleError(errors) }
}