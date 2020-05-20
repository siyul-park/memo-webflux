package com.ara.memo.util.error

interface WrappedErrorProvider : ErrorProvider {
    val error: Error

    override fun get() = error
    override fun type() = error.javaClass
}