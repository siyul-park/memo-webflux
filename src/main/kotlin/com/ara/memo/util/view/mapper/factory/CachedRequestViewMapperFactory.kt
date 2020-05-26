package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.Cache
import com.ara.memo.util.cache.WeakCache
import com.ara.memo.util.cache.getOrSet
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.RequestViewMapper

class CachedRequestViewMapperFactory(
    private val cache: Cache<MappingInfo<*, *>, RequestViewMapper<*, *>> = WeakCache()
) : RequestViewMapperFactory {
    override fun <V : Any, H : Any> create(mappingInfo: MappingInfo<V, H>): RequestViewMapper<V, H> = cache.getOrSet(mappingInfo) {
        RequestViewMapper(mappingInfo)
    } as RequestViewMapper<V, H>
}