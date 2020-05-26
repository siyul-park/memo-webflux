package com.ara.memo.util.cache.factory

import com.ara.memo.util.cache.Cache

interface CacheFactory {
    fun <K : Any, V : Any> create(): Cache<K, V>
}