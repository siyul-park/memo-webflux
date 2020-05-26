package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.RequestViewMapper

interface RequestViewMapperFactory {
    fun <V: Any, H: Any> create(mappingInfo: MappingInfo<V, H>): RequestViewMapper<V, H>
}