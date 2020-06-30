package com.ara.memo.jackson.model

import kotlin.reflect.KClass

data class JsonView<T : Any>(
    val value: T,
    val view: KClass<*>? = null
) {
    companion object {
        fun <T : Any> of(value: T, view: KClass<*>?) = JsonView(value, view)
    }
}