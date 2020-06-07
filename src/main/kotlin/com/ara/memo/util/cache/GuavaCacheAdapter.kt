package com.ara.memo.util.cache

class GuavaCacheAdapter<K : Any, V : Any>(
    private val guavaCache: com.google.common.cache.Cache<K, V>
) : Cache<K, V> {
    override val size: Long = guavaCache.size()

    override fun get(key: K): V? = guavaCache.getIfPresent(key)

    override fun set(key: K, value: V): V? {
        val pre = guavaCache.getIfPresent(key)

        guavaCache.put(key, value)
        return pre
    }

    override fun remove(key: K): V? {
        val pre = guavaCache.getIfPresent(key)

        guavaCache.invalidate(key)
        return pre
    }

    override fun getOrSet(key: K, defaultValue: () -> V): V = guavaCache.get(key, defaultValue)

    override fun clear() = guavaCache.cleanUp()
}