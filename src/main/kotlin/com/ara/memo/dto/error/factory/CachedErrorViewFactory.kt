package com.ara.memo.dto.error.factory

import com.ara.memo.dto.error.ErrorView
import com.ara.memo.dto.error.MultipleErrorView
import com.ara.memo.dto.error.SingleErrorView
import com.ara.memo.util.cache.LocalCache
import com.ara.memo.util.cache.getOrPut
import com.ara.memo.util.error.Error
import com.ara.memo.util.error.MultipleError
import com.ara.memo.util.error.SingleError

class CachedErrorViewFactory : ErrorViewFactory {
    private data class ViewRequest(
        val path: String,
        val name: String,
        val error: Error?
    )

    private val singleErrorViewCache = LocalCache<ViewRequest, SingleErrorView>()
    private val multipleErrorViewCache = LocalCache<ViewRequest, MultipleErrorView>()

    override fun of(path: String, name: String, error: Error?) = when (error) {
        is SingleError -> of(path, name, error)
        is MultipleError -> of(path, name, error)
        else -> ErrorView(path, name, error)
    }

    override fun of(path: String, name: String, error: SingleError?): SingleErrorView {
        return singleErrorViewCache.getOrPut(ViewRequest(path, name, error)) {
            SingleErrorView(path, name, error)
        }
    }

    override fun of(path: String, name: String, error: MultipleError?): MultipleErrorView {
        return multipleErrorViewCache.getOrPut(ViewRequest(path, name, error)) {
            MultipleErrorView(path, name, error)
        }
    }
}