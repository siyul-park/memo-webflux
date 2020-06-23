package com.ara.memo.util.value.path

import com.ara.memo.util.value.mapper.ValueMappers
import com.ara.memo.util.value.path.exception.CantFindPathVariableException
import kotlin.reflect.KClass

class PathVariableExtractor(
    private val pathVariables: Map<String, String>,
    private val mappers: ValueMappers
) {
    fun extract(key: String) = extract(key, String::class)

    fun <T : Any> extract(key: String, type: KClass<T>): T {
        val value = pathVariables[key] ?: throw CantFindPathVariableException("Cant find $key")
        val mapper = mappers.get(type)

        return mapper.map(value)
    }
}