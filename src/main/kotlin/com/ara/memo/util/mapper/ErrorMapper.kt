package com.ara.memo.util.mapper

import com.ara.memo.entity.error.Error

interface ErrorMapper : Mapper<Throwable, Error> {
    override fun map(source: Throwable): Error
}