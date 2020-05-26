package com.ara.memo.util.view.mapper

import com.ara.memo.util.mapper.Mapper
import org.springframework.http.codec.json.Jackson2CodecSupport
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

class ResponseViewMapper<V: Any, H: Any>(
    private val mappingInfo: MappingInfo<V, H>,
    private val createServerResponseBodyBuilder: () -> ServerResponse.BodyBuilder
) : Mapper<Mono<V>, Mono<ServerResponse>> {
    override fun map(source: Mono<V>) = createServerResponseBodyBuilder().hint(
        Jackson2CodecSupport.JSON_VIEW_HINT, mappingInfo.hint.java
    ).body(BodyInserters.fromPublisher(source, mappingInfo.view.java))
}