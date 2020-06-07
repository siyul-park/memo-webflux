package com.ara.memo.util.error.factory

import com.ara.memo.util.cache.Cache
import com.ara.memo.util.cache.getOrSet
import com.ara.memo.util.error.Error
import com.ara.memo.util.error.MultipleError
import com.ara.memo.util.error.SingleError

class CachedErrorFactory(
    private val singleErrorCache: Cache<Any, SingleError>,
    private val multipleErrorCache: Cache<Any, MultipleError>
) : ErrorFactory {
    override fun from(message: String?) = singleErrorCache.getOrSet(message.hashCode()) { SingleError(message) }
    override fun from(errors: Collection<SingleError>) = multipleErrorCache.getOrSet(errors.hashCode()) { MultipleError(errors) }
}