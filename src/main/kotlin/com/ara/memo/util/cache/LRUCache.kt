package com.ara.memo.util.cache

class LRUCache<K : Any, V : Any>(
    private val delegate: Cache<K, V>,
    private val minimalSize: Int = DEFAULT_SIZE
) : Cache<K, V> {
    private val keyMap = object : LinkedHashMap<Any, Any>(
        minimalSize, .75f, true
    ) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Any, Any>): Boolean {
            val tooManyCachedItems = size > minimalSize
            if (tooManyCachedItems) eldestKeyToRemove = eldest.key
            return tooManyCachedItems
        }
    }

    private var eldestKeyToRemove: Any? = null

    override val size: Int
        get() = delegate.size

    override fun set(key: K?, value: V): V? {
        val result = delegate.set(key, value)
        cycleKeyMap(key)
        return result
    }

    override fun remove(key: K?) = delegate.remove(key)

    override fun get(key: K?): V? {
        keyMap[maskNull(key)]
        return delegate[key]
    }

    override fun clear() {
        keyMap.clear()
        delegate.clear()
    }

    private fun cycleKeyMap(key: K?) {
        keyMap[maskNull(key)] = PRESENT
        eldestKeyToRemove?.let { delegate.remove(unmaskNull(it)) }
        eldestKeyToRemove = null
    }

    private fun maskNull(key: K?) = key ?: NULL_KEY

    private fun unmaskNull(key: Any) = when (key == NULL_KEY) {
        true -> null
        false -> key as K
    }

    companion object {
        private val NULL_KEY = Any()
        private const val DEFAULT_SIZE = 100
        private const val PRESENT = true
    }
}