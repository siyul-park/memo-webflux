package com.ara.memo.util.view.mapper

import kotlin.reflect.KClass

data class MappingInfo<V: Any, H: Any>(
    val view: KClass<V>,
    val hint: KClass<H>
)