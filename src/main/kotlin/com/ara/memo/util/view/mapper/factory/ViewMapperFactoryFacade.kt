package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.view.mapper.MappingInfo
import org.springframework.web.reactive.function.server.ServerResponse

class ViewMapperFactoryFacade(
    private val requestViewMapperFactory: RequestViewMapperFactory,
    private val responseViewMapperFactory: ResponseViewMapperFactory
) : ViewMapperFactory {
    override fun <V : Any, H : Any> createRequestViewMapper(mappingInfo: MappingInfo<V, H>) = requestViewMapperFactory.create(mappingInfo)
    override fun <V : Any, H : Any> createResponseViewMapper(
        mappingInfo: MappingInfo<V, H>,
        createServerResponseBodyBuilder: () -> ServerResponse.BodyBuilder
    ) = responseViewMapperFactory.create(mappingInfo, createServerResponseBodyBuilder)
}