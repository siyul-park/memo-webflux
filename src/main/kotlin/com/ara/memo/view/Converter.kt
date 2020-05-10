package com.ara.memo.view

import com.ara.memo.database.entity.Entity

interface Converter<E : Entity<ID>, V : View, ID> {
    fun toView(entity: E): V

    fun toEntity(view: V): E
}