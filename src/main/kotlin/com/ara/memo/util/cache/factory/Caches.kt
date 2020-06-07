package com.ara.memo.util.cache.factory

import com.ara.memo.util.cache.GuavaCacheAdapter
import com.google.common.cache.Cache
import java.util.*

object Caches {
    fun <K : Any, V : Any> from(guavaCache: Cache<Optional<K>, V>) = GuavaCacheAdapter(guavaCache)
    fun <K : Any, V : Any> weak() = WeakCacheFactory.create<K, V>()
}