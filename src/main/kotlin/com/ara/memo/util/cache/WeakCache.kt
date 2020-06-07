package com.ara.memo.util.cache

import java.util.*
import java.util.concurrent.ConcurrentHashMap

class WeakCache<K : Any, V : Any> : Cache<K, V> {
    private val map: MutableMap<Any, V> = ConcurrentHashMap(WeakHashMap())

    override val size: Long = map.size.toLong()

    override fun get(key: K?) = map[maskNull(key)]

    override fun set(key: K?, value: V) = map.put(maskNull(key), value)

    override fun remove(key: K?) = map.remove(maskNull(key))

    override fun clear() = map.clear()

    private fun maskNull(key: K?) = key ?: NULL_KEY

    companion object {
        private val NULL_KEY = Any()
    }
}
