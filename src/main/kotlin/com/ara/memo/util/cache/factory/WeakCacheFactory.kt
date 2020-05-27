package com.ara.memo.util.cache.factory

import com.ara.memo.util.cache.Cache
import com.ara.memo.util.cache.WeakCache

object WeakCacheFactory : CacheFactory {
    override fun <K : Any, V : Any> create(): Cache<K, V> = WeakCache()
}