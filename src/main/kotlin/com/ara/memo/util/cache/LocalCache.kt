package com.ara.memo.util.cache

import java.util.*

class LocalCache<K : Any, V : Any> : Cache<K, V> {
    private val map: WeakHashMap<K, V> = WeakHashMap()

    override val size = map.size

    override fun get(key: K?) = map[key]

    override fun put(key: K?, value: V) = map.put(key, value)

    override fun clear() = map.clear()
}
