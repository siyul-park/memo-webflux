package com.ara.memo.util.mapper

interface BiMergeMapper<P0 : Any, P1 : Any, R : Any> {
    fun mapWith(p0: P0, p1: P1): R
}