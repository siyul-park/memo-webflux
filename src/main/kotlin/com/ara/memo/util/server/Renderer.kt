package com.ara.memo.util.server

import com.ara.memo.util.jackson.JacksonViewHintPatch
import org.reactivestreams.Publisher
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import java.net.URI
import kotlin.reflect.KClass

class Renderer<V : Any, H : Any>(
    private val viewType: KClass<V>,
    viewHint: KClass<H>
) {
    private val hintPatch = JacksonViewHintPatch(viewHint)

    fun <P : Publisher<V>> renderCreatedResponse(view: P, uri: URI) = hintPatch.apply(
        ServerResponse.created(uri), ServerResponse.BodyBuilder::hint
    ).body(BodyInserters.fromPublisher(view, viewType.java))

    fun <P : Publisher<V>> renderOkResponse(view: P) = hintPatch.apply(
        ServerResponse.ok(), ServerResponse.BodyBuilder::hint
    ).body(BodyInserters.fromPublisher(view, viewType.java))

    fun renderNoContentResponse() = ServerResponse.noContent().build()

    companion object {
        fun <V : Any> from(viewType: KClass<V>) = Renderer(viewType, Unit::class)
        fun <V : Any, H : Any> of(viewType: KClass<V>, viewHint: KClass<H>) = Renderer(viewType, viewHint)
    }
}