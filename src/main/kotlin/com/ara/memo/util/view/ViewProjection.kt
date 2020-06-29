package com.ara.memo.util.view

import kotlin.reflect.KClass

data class ViewProjection<T : Any>(
    val view: View<T>,
    val fields: Collection<String>
) {
    companion object {
        fun <T : Any> of(view: View<T>, fields: Collection<String>): ViewProjection<T> {
            return ViewProjection(view, fields)
        }

        fun <T : Any> of(value: T, fields: Collection<String>, view: KClass<*>? = null): ViewProjection<T> {
            return ViewProjection(View.of(value, view), fields)
        }
    }
}