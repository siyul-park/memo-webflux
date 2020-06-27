package com.ara.memo.util.refrect

import kotlin.reflect.KClass

object KotlinExtentions {
    fun convertToClass(clazz: Array<out KClass<*>>): Array<Class<out Any>> {
        return clazz.map { it.java }.toTypedArray()
    }
}