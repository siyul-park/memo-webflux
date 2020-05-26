package com.ara.memo.util.view.mapper

import com.ara.memo.util.mapper.Mapper
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono

class RequestViewMapper<V: Any, H: Any>(
    private val mappingInfo: MappingInfo<V, H>
) : Mapper<ServerRequest, Mono<V>> {
    override fun map(source: ServerRequest) = source.body(
        BodyExtractors.toMono(mappingInfo.view.java),
        mapOf(
            Jackson2CodecSupport.JSON_VIEW_HINT to mappingInfo.hint.java
        )
    )
}