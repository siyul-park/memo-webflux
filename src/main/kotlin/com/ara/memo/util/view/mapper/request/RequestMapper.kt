package com.ara.memo.util.view.mapper.request

import com.ara.memo.util.mapper.Mapper
import com.ara.memo.util.view.mapper.MappingInfo
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono

class RequestMapper<V : Any, H : Any>(
    private val mappingInfo: MappingInfo<V, H>
) : Mapper<ServerRequest, Mono<V>> {
    override fun map(source: ServerRequest): Mono<V> = when (mappingInfo.hint == Unit::class) {
        true -> source.body(BodyExtractors.toMono(mappingInfo.view.java))
        false -> source.body(
            BodyExtractors.toMono(mappingInfo.view.java),
            mapOf(
                Jackson2CodecSupport.JSON_VIEW_HINT to mappingInfo.hint.java
            )
        )
    }
}