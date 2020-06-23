package com.ara.memo.util.view.mapper.response

import com.ara.memo.util.mapper.Mapper
import com.ara.memo.util.view.mapper.MappingInfo
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

class ResponseMapper<V : Any, H : Any>(
    private val mappingInfo: MappingInfo<V, H>,
    private val createServerResponseBodyBuilder: (source: V) -> ServerResponse.BodyBuilder
) : Mapper<Mono<V>, Mono<ServerResponse>> {
    fun map(source: V) = map(Mono.just(source))

    override fun map(source: Mono<V>) = source.map {
        createServerResponseBodyBuilder(it)
    }.map {
        when (mappingInfo.hint != Unit::class) {
            true -> it.hint(Jackson2CodecSupport.JSON_VIEW_HINT, mappingInfo.hint.java)
            false -> it
        }
    }.flatMap { it.body(BodyInserters.fromPublisher(source, mappingInfo.view.java)) }
}