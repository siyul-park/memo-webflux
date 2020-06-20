package com.ara.memo.util.provider

import kotlin.reflect.KClass

interface Provider<T : Any> {
    fun get(): T

    fun type(): KClass<out T>
}