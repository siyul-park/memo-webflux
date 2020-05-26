package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.RequestViewMapper
import com.ara.memo.util.view.mapper.ResponseViewMapper
import org.springframework.web.reactive.function.server.ServerResponse

interface ViewMapperFactory {
    fun <V: Any, H: Any> createRequestViewMapper(mappingInfo: MappingInfo<V, H>): RequestViewMapper<V, H>
    fun <V: Any, H: Any> createResponseViewMapper(
        mappingInfo: MappingInfo<V, H>,
        createServerResponseBodyBuilder: () -> ServerResponse.BodyBuilder
    ): ResponseViewMapper<V, H>
}