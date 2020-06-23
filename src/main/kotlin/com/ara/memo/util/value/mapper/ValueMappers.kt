package com.ara.memo.util.value.mapper

import com.ara.memo.util.value.mapper.exception.CantFindMapperException
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

class ValueMappers {
    private val map: MutableMap<KClass<*>, ValueMapper<*>> = ConcurrentHashMap()

    fun <T : Any> set(clazz: KClass<T>, mapper: ValueMapper<T>) {
        map[clazz] = mapper
    }

    fun <T : Any> get(clazz: KClass<T>): ValueMapper<T> {
        return map[clazz] as? ValueMapper<T> ?: throw CantFindMapperException("Not defined mapper")
    }
}