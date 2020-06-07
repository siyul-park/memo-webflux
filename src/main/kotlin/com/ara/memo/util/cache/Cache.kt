package com.ara.memo.util.cache

interface Cache<K : Any, V : Any> {
    val size: Long

    operator fun get(key: K): V?
    operator fun set(key: K, value: V): V?
    fun getOrSet(key: K, defaultValue: () -> V): V
    fun remove(key: K): V?
    fun clear()
}