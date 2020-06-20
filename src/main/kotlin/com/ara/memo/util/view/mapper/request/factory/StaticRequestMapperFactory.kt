package com.ara.memo.util.view.mapper.request.factory

import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.request.RequestMapper

class StaticRequestMapperFactory : RequestMapperFactory {
    override fun <V : Any, H : Any> from(mappingInfo: MappingInfo<V, H>) = RequestMapper(mappingInfo)
}