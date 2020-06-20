package com.ara.memo.util.view.mapper.response.factory

import com.ara.memo.util.cache.Cache
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.response.ResponseMapper
import org.springframework.web.reactive.function.server.ServerResponse

class CachedResponseMapperFactory(
    private val cache: Cache<Any, ResponseMapper<*, *>>
) : ResponseMapperFactory {
    override fun <V : Any, H : Any> from(
        mappingInfo: MappingInfo<V, H>,
        createServerResponseBodyBuilder: (source: V) -> ServerResponse.BodyBuilder
    ): ResponseMapper<V, H> = cache.getOrSet((mappingInfo to createServerResponseBodyBuilder).hashCode()) {
        ResponseMapper(mappingInfo, createServerResponseBodyBuilder)
    } as ResponseMapper<V, H>
}