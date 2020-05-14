package com.ara.memo.dao.mapper

interface ExceptionMapper {
    fun map(error: Throwable): Throwable
}