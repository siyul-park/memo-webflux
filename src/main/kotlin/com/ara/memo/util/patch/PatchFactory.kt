package com.ara.memo.util.patch

import kotlin.reflect.KClass

interface PatchFactory {
    fun <O: Any, T: Any> create(overrides: O, type: KClass<T>): Patch<T>
}