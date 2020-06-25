package com.ara.memo.util.cache.factory

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import java.time.Duration

object GuavaCacheFactory {
    fun <K : Any, V : Any> dynamic(): Cache<K, V> = CacheBuilder.newBuilder()
        .softValues()
        .expireAfterAccess(Duration.ofHours(1))
        .build()
}