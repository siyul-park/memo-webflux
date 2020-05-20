package com.ara.memo.util.error.factory

import com.ara.memo.util.error.MultipleError
import com.ara.memo.util.error.SingleError

interface ErrorFactory {
    fun from(message: String? = null): SingleError
    fun from(errors: Collection<SingleError>): MultipleError
}