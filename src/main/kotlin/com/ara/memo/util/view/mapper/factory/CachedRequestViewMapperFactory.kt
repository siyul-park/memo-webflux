package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.Cache
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.RequestViewMapper

class CachedRequestViewMapperFactory(
    private val cache: Cache<Any, RequestViewMapper<*, *>>
) : RequestViewMapperFactory {
    override fun <V : Any, H : Any> from(mappingInfo: MappingInfo<V, H>): RequestViewMapper<V, H> = cache.getOrSet(mappingInfo.hashCode()) {
        RequestViewMapper(mappingInfo)
    } as RequestViewMapper<V, H>
}