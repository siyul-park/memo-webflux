package com.ara.memo.util.error.factory

import com.ara.memo.util.error.MultipleError
import com.ara.memo.util.error.PrivateError
import com.ara.memo.util.error.PublicError
import com.ara.memo.util.error.SingleError

interface ErrorFactory {
    fun of(message: String? = null, privateMessage: String? = null): PrivateError
    fun from(message: String? = null): PublicError
    fun from(errors: Collection<SingleError>): MultipleError
}