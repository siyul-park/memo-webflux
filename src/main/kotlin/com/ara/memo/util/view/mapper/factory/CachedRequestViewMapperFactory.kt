package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.WeakCache
import com.ara.memo.util.cache.getOrSet
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.RequestViewMapper

class CachedRequestViewMapperFactory : RequestViewMapperFactory {
    private val cache = WeakCache<MappingInfo<*, *>, RequestViewMapper<*, *>>()

    override fun <V : Any, H : Any> create(mappingInfo: MappingInfo<V, H>): RequestViewMapper<V, H> = cache.getOrSet(mappingInfo) {
        RequestViewMapper(mappingInfo)
    } as RequestViewMapper<V, H>
}