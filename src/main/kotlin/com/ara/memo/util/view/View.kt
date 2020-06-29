package com.ara.memo.util.view

import kotlin.reflect.KClass

data class View<T : Any>(
    val value: T,
    val view: KClass<*>? = null
) {
    companion object {
        fun <T : Any> of(value: T, view: KClass<*>?) = View(value, view)
    }
}