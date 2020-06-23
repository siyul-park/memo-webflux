package com.ara.memo.util.patch

interface Patch<T> {
    fun apply(obj: T): T
}