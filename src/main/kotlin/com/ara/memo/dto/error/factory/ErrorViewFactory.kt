package com.ara.memo.dto.error.factory

import com.ara.memo.dto.error.ErrorView
import com.ara.memo.dto.error.MultipleErrorView
import com.ara.memo.dto.error.SingleErrorView
import com.ara.memo.util.error.Error
import com.ara.memo.util.error.MultipleError
import com.ara.memo.util.error.SingleError

interface ErrorViewFactory {
    fun of(path: String, name: String, error: Error? = null): ErrorView
    fun of(path: String, name: String, error: SingleError? = null): SingleErrorView
    fun of(path: String, name: String, error: MultipleError? = null): MultipleErrorView
}