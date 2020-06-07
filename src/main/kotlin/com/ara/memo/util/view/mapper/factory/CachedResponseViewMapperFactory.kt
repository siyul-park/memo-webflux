package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.Cache
import com.ara.memo.util.cache.getOrSet
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.ResponseViewMapper
import org.springframework.web.reactive.function.server.ServerResponse

class CachedResponseViewMapperFactory(
    private val cache: Cache<Any, ResponseViewMapper<*, *>>
) : ResponseViewMapperFactory {
    override fun <V : Any, H : Any> of(
        mappingInfo: MappingInfo<V, H>,
        createServerResponseBodyBuilder: () -> ServerResponse.BodyBuilder
    ): ResponseViewMapper<V, H> = cache.getOrSet((mappingInfo to createServerResponseBodyBuilder).hashCode()) {
        ResponseViewMapper(mappingInfo, createServerResponseBodyBuilder)
    } as ResponseViewMapper<V, H>
}