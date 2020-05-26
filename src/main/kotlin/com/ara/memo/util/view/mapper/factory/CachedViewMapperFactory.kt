package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.RequestViewMapper
import org.springframework.web.reactive.function.server.ServerResponse

class CachedViewMapperFactory : ViewMapperFactory {
    private val cachedRequestViewMapperFactory = CachedRequestViewMapperFactory()
    private val cachedResponseViewMapperFactory = CachedResponseViewMapperFactory()

    override fun <V: Any, H: Any> createRequestViewMapper(mappingInfo: MappingInfo<V, H>) = cachedRequestViewMapperFactory.create(mappingInfo)
    override fun <V: Any, H: Any> createResponseViewMapper(
        mappingInfo: MappingInfo<V, H>,
        createServerResponseBodyBuilder: () -> ServerResponse.BodyBuilder
    ) = cachedResponseViewMapperFactory.create(mappingInfo, createServerResponseBodyBuilder)
}