package com.ara.memo.dto.error.factory

import com.ara.memo.dto.error.ErrorView
import com.ara.memo.dto.error.MultipleErrorView
import com.ara.memo.dto.error.SingleErrorView
import com.ara.memo.util.cache.Cache
import com.ara.memo.util.error.Error
import com.ara.memo.util.error.MultipleError
import com.ara.memo.util.error.SingleError

class CachedErrorViewFactory(
    private val singleErrorViewCache: Cache<Any, SingleErrorView>,
    private val multipleErrorViewCache: Cache<Any, MultipleErrorView>
) : ErrorViewFactory {
    data class ViewRequest(
        val path: String,
        val name: String,
        val error: Error?
    )

    override fun of(path: String, name: String, error: Error?) = when (error) {
        is SingleError -> of(path, name, error)
        is MultipleError -> of(path, name, error)
        else -> ErrorView(path, name, error)
    }

    override fun of(path: String, name: String, error: SingleError?) = singleErrorViewCache.getOrSet(ViewRequest(path, name, error).hashCode()) {
        SingleErrorView(path, name, error)
    }


    override fun of(path: String, name: String, error: MultipleError?) = multipleErrorViewCache.getOrSet(ViewRequest(path, name, error).hashCode()) {
        MultipleErrorView(path, name, error)
    }
}