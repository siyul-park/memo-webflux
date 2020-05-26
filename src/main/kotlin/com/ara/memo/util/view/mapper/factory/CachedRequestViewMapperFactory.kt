package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.LocalCache
import com.ara.memo.util.cache.getOrPut
import com.ara.memo.util.error.SingleError
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.RequestViewMapper

class CachedRequestViewMapperFactory : RequestViewMapperFactory {
    private val cache = LocalCache<MappingInfo<*, *>, RequestViewMapper<*, *>>()

    override fun <V: Any, H: Any> create(mappingInfo: MappingInfo<V, H>): RequestViewMapper<V, H> = cache.getOrPut(mappingInfo) {
        RequestViewMapper(mappingInfo)
    } as RequestViewMapper<V, H>
}