package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.LocalCache
import com.ara.memo.util.cache.getOrPut
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.ResponseViewMapper
import org.springframework.web.reactive.function.server.ServerResponse

class CachedResponseViewMapperFactory : ResponseViewMapperFactory {
    private val cache = LocalCache<Pair<MappingInfo<*, *>, () -> ServerResponse.BodyBuilder>, ResponseViewMapper<*, *>>()

    override fun <V: Any, H: Any> create(
        mappingInfo: MappingInfo<V, H>,
        createServerResponseBodyBuilder: () -> ServerResponse.BodyBuilder
    ): ResponseViewMapper<V, H> = cache.getOrPut(mappingInfo to createServerResponseBodyBuilder) {
        ResponseViewMapper(mappingInfo, createServerResponseBodyBuilder)
    } as ResponseViewMapper<V, H>
}