package com.ara.memo.jackson.model

data class JsonProjection<T : Any>(
    val view: T,
    val fields: Fields
) {
    companion object {
        fun <T : Any> of(view: T, fields: Collection<String>): JsonProjection<T> {
            return JsonProjection(view, Fields.from(fields))
        }
    }
}