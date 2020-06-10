package com.ara.memo.util.error.factory

import com.ara.memo.util.cache.Cache
import com.ara.memo.util.error.MultipleError
import com.ara.memo.util.error.PrivateError
import com.ara.memo.util.error.PublicError
import com.ara.memo.util.error.SingleError

class CachedErrorFactory(
    private val publicErrorCache: Cache<Any, PublicError>,
    private val privateErrorCache: Cache<Any, PrivateError>,
    private val multipleErrorCache: Cache<Any, MultipleError>
) : ErrorFactory {
    override fun of(message: String?, privateMessage: String?) = privateErrorCache.getOrSet(Pair(message, privateMessage).hashCode()) { PrivateError(message, privateMessage) }
    override fun from(message: String?) = publicErrorCache.getOrSet(message.hashCode()) { PublicError(message) }
    override fun from(errors: Collection<SingleError>) = multipleErrorCache.getOrSet(errors.hashCode()) { MultipleError(errors) }
}