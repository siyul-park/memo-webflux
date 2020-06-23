package com.ara.memo.util.view.mapper

import kotlin.reflect.KClass

data class MappingInfo<V : Any, H : Any>(
    val view: KClass<V>,
    val hint: KClass<H>
) {
    companion object {
        fun <V : Any> from(view: KClass<V>) = MappingInfo(view, Unit::class)
        fun <V : Any, H : Any> of(view: KClass<V>, hint: KClass<H>) = MappingInfo(view, hint)
    }
}