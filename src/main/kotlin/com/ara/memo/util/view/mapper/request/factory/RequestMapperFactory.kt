package com.ara.memo.util.view.mapper.request.factory

import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.request.RequestMapper

interface RequestMapperFactory {
    fun <V : Any, H : Any> from(mappingInfo: MappingInfo<V, H>): RequestMapper<V, H>
}