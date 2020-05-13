package com.ara.memo.dto

import com.ara.memo.entity.Entity

interface Dto<E: Entity<ID>, ID> {
    fun toEntity(): E
}