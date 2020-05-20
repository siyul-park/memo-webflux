package com.ara.memo.util.error

import com.ara.memo.util.cache.LocalCache
import com.ara.memo.util.cache.getOrPut

class CacheErrorFactory : ErrorFactory {
    private val singleErrorCache = LocalCache<String, SingleError>()
    private val multipleErrorCache = LocalCache<Collection<Error>, MultipleError>()

    override fun of(message: String?) = singleErrorCache.getOrPut(message) { SingleError(message) }
    override fun of(errors: Collection<Error>) = multipleErrorCache.getOrPut(errors) { MultipleError(errors) }
}