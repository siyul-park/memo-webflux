package com.ara.memo.util.server

import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

fun ServerRequest.hint(key: String, value: Any) = with(ServerRequestHintHelper(this)) {
    hint(key, value)
}

fun <T : Any> ServerRequest.bodyToMono(elementClass: KClass<T>): Mono<T> = this.bodyToMono(elementClass.java)
fun <T : Any> ServerRequest.bodyToFlux(elementClass: KClass<T>): Flux<T> = this.bodyToFlux(elementClass.java)

class ServerRequestHintHelper(
    private val serverRequest: ServerRequest
) : ServerRequest by serverRequest {
    private val hints: MutableMap<String, Any> = ConcurrentHashMap()

    fun hint(key: String, value: Any): ServerRequestHintHelper {
        hints[key] = value
        return this
    }
}