package com.ara.memo.util.cache.factory

object Caches {
    fun <K : Any, V : Any> dynamic() = DynamicCacheFactory.create<K, V>()
    fun <K : Any, V : Any> weak() = WeakCacheFactory.create<K, V>()
}