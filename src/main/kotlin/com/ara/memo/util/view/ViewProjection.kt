package com.ara.memo.util.view

import kotlin.reflect.KClass

data class ViewProjection<T : Any>(
    val view: T,
    val fields: Fields
) {
    companion object {
        fun <T : Any> of(view: View<T>, fields: Collection<String>): ViewProjection<View<T>> {
            return ViewProjection(view, Fields.from(fields))
        }

        fun <T : Any> of(value: T, fields: Collection<String>, view: KClass<*>? = null): ViewProjection<View<T>> {
            return ViewProjection(View.of(value, view), Fields.from(fields))
        }
    }
}