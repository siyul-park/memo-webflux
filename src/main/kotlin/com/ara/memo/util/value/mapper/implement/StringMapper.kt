package com.ara.memo.util.value.mapper.implement

import com.ara.memo.util.value.mapper.ValueMapper

object StringMapper : ValueMapper<String> {
    override fun map(source: String) = source
}