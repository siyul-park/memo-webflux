package com.ara.memo.util.patch

class LambdaPatch<T>(
    private val updater: T.() -> Unit
) : Patch<T> {
    override fun apply(obj: T): T {
        updater(obj)
        return obj
    }

    companion object {
        fun <T> from(updater: T.() -> Unit) = LambdaPatch(updater)
    }
}