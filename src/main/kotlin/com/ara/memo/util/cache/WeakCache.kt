package com.ara.memo.util.cache

import java.util.*
import java.util.concurrent.ConcurrentHashMap

class WeakCache<K : Any, V : Any> : Cache<K, V> {
    private val map: MutableMap<Any, V> = ConcurrentHashMap(WeakHashMap())

    override val size: Long = map.size.toLong()

    override fun get(key: K) = map[key]

    override fun set(key: K, value: V) = map.put(key, value)

    override fun remove(key: K) = map.remove(key)

    override fun getOrSet(key: K, defaultValue: () -> V): V {
        val value = map[key]
        return if (value == null) {
            val answer = defaultValue()
            map[key] = answer
            answer
        } else {
            value
        }
    }

    override fun clear() = map.clear()
}
