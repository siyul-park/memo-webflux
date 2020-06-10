package com.ara.memo.dto.error.factory

import com.ara.memo.dto.error.ErrorView
import com.ara.memo.dto.error.MultipleErrorView
import com.ara.memo.dto.error.SingleErrorView
import com.ara.memo.util.cache.Cache
import com.ara.memo.util.error.*
import com.ara.memo.util.error.factory.Errors

class CachedErrorViewFactory(
    private val singleErrorViewCache: Cache<Any, SingleErrorView>,
    private val multipleErrorViewCache: Cache<Any, MultipleErrorView>
) : ErrorViewFactory {
    override fun of(path: String, name: String, error: Error?) = when (error) {
        is SingleError -> of(path, name, error)
        is MultipleError -> of(path, name, error)
        else -> ErrorView(path, name, error)
    }

    override fun of(path: String, name: String, error: SingleError?) = singleErrorViewCache.getOrSet(Triple(path, name, error).hashCode()) {
        when (error) {
            is PublicError -> of(path, name, error)
            is PrivateError -> of(path, name, error)
            else -> SingleErrorView(path, name, error)
        }
    }

    private fun of(path: String, name: String, error: PrivateError?) = SingleErrorView(path, name, Errors.from(error?.message))

    private fun of(path: String, name: String, error: PublicError?) = SingleErrorView(path, name, error)

    override fun of(path: String, name: String, error: MultipleError?) = multipleErrorViewCache.getOrSet(Triple(path, name, error).hashCode()) {
        MultipleErrorView(path, name, error)
    }
}