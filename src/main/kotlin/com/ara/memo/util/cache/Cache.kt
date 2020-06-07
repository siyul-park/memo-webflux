package com.ara.memo.util.cache

interface Cache<K : Any, V : Any> {
    val size: Long

    operator fun get(key: K?): V?
    operator fun set(key: K?, value: V): V?
    fun remove(key: K?): V?
    fun clear()
}

inline fun <K : Any, V : Any> Cache<K, V>.getOrSet(key: K?, defaultValue: () -> V): V {
    val value = get(key)
    return if (value == null) {
        val answer = defaultValue()
        set(key, answer)
        answer
    } else {
        value
    }
}