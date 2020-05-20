package com.ara.memo.util.error.factory

import com.ara.memo.util.cache.LocalCache
import com.ara.memo.util.cache.getOrPut
import com.ara.memo.util.error.Error
import com.ara.memo.util.error.MultipleError
import com.ara.memo.util.error.SingleError

class CachedErrorFactory : ErrorFactory {
    private val singleErrorCache = LocalCache<String, SingleError>()
    private val multipleErrorCache = LocalCache<Collection<Error>, MultipleError>()

    override fun from(message: String?) = singleErrorCache.getOrPut(message) { SingleError(message) }
    override fun from(errors: Collection<SingleError>) = multipleErrorCache.getOrPut(errors) { MultipleError(errors) }
}