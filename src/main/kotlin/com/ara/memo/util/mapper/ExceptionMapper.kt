package com.ara.memo.util.mapper

interface ExceptionMapper : Mapper<Throwable, Throwable> {
    override fun map(source: Throwable): Throwable
}