package com.ara.memo.util.view.mapper.response.factory

import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.response.ResponseMapper
import org.springframework.web.reactive.function.server.ServerResponse

interface ResponseMapperFactory {
    fun <V : Any, H : Any> from(
        mappingInfo: MappingInfo<V, H>,
        createServerResponseBodyBuilder: (source: V) -> ServerResponse.BodyBuilder
    ): ResponseMapper<V, H>
}