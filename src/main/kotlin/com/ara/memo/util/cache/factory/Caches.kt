package com.ara.memo.util.cache.factory

import com.ara.memo.util.cache.GuavaCacheAdapter
import com.ara.memo.util.cache.WeakCache
import com.google.common.cache.Cache

object Caches {
    fun <K : Any, V : Any> from(guavaCache: Cache<K, V>) = GuavaCacheAdapter(guavaCache)
    fun <K : Any, V : Any> weak() = WeakCache<K, V>()
}