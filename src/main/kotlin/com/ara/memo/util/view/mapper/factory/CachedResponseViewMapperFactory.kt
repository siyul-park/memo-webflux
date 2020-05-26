package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.WeakCache
import com.ara.memo.util.cache.getOrSet
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.ResponseViewMapper
import org.springframework.web.reactive.function.server.ServerResponse

class CachedResponseViewMapperFactory : ResponseViewMapperFactory {
    private val cache = WeakCache<Pair<MappingInfo<*, *>, () -> ServerResponse.BodyBuilder>, ResponseViewMapper<*, *>>()

    override fun <V : Any, H : Any> create(
        mappingInfo: MappingInfo<V, H>,
        createServerResponseBodyBuilder: () -> ServerResponse.BodyBuilder
    ): ResponseViewMapper<V, H> = cache.getOrSet(mappingInfo to createServerResponseBodyBuilder) {
        ResponseViewMapper(mappingInfo, createServerResponseBodyBuilder)
    } as ResponseViewMapper<V, H>
}