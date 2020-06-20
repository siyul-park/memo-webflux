package com.ara.memo.util.error

interface WrappedErrorProvider<T : Error> : ErrorProvider<T> {
    val error: T

    override fun get() = error
    override fun type() = error::class
}