package com.ara.memo.util.mapper

interface Mapper<S : Any, R : Any> {
    fun map(source: S): R
}