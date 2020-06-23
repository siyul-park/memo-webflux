package com.ara.memo.util.server

import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import kotlin.reflect.KClass

fun <T : Any> ServerRequest.bodyToMono(elementClass: KClass<T>): Mono<T> = this.bodyToMono(elementClass.java)
fun <T : Any> ServerRequest.bodyToFlux(elementClass: KClass<T>): Flux<T> = this.bodyToFlux(elementClass.java)
