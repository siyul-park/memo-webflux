package com.ara.memo.util.value.mapper.implement

import com.ara.memo.util.value.mapper.ValueMapper

object IntMapper : ValueMapper<Int> {
    override fun map(source: String) = Integer.parseInt(source)
}