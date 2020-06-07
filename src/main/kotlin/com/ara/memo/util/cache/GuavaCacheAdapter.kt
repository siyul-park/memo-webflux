package com.ara.memo.util.cache

import java.util.*

class GuavaCacheAdapter<K : Any, V : Any>(
    private val guavaCache: com.google.common.cache.Cache<Optional<K>, V>
) : Cache<K, V> {
    override val size: Long = guavaCache.size()

    override fun get(key: K?): V? = guavaCache.getIfPresent(Optional.ofNullable(key))

    override fun set(key: K?, value: V): V? {
        val maskedKey = Optional.ofNullable(key)
        val pre = guavaCache.getIfPresent(maskedKey)

        guavaCache.put(maskedKey, value)
        return pre
    }

    override fun remove(key: K?): V? {
        val maskedKey = Optional.ofNullable(key)
        val pre = guavaCache.getIfPresent(maskedKey)

        guavaCache.invalidate(maskedKey)
        return pre
    }

    override fun clear() = guavaCache.cleanUp()
}