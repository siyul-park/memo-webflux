package com.ara.memo.util.view

import java.util.stream.Collectors

data class Fields(
    private val fields: Collection<Field>
) : MutableMap<String, Field> by HashMap(
    fields.stream().collect(Collectors.toMap(Field::name) { it })
) {
    companion object {
        fun from(fields: Collection<String>): Fields {
            val map = Fields(setOf())
            fields.map { it.split(".") }
                .forEach { list ->
                    var current = map
                    list.forEach { name ->
                        val field = current.getOrPut(name) { Field(name) }
                        current = field.children
                    }
                }
            return map
        }
    }
}