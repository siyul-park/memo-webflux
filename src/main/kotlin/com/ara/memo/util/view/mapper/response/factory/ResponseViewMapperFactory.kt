package com.ara.memo.util.view.mapper.response.factory

import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.response.ResponseViewMapper
import org.springframework.web.reactive.function.server.ServerResponse

interface ResponseViewMapperFactory {
    fun <V : Any, H : Any> from(
        mappingInfo: MappingInfo<V, H>,
        createServerResponseBodyBuilder: () -> ServerResponse.BodyBuilder
    ): ResponseViewMapper<V, H>
}