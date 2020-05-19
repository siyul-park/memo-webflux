package com.ara.memo.util.plugin

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler

class ScheduleSubscribePlugin(
    private val scheduler: Scheduler
) : Plugin {
    override fun <T> apply(mono: Mono<T>): Mono<T> {
        return mono.subscribeOn(scheduler)
    }

    override fun <T> apply(flux: Flux<T>): Flux<T> {
        return flux.subscribeOn(scheduler)
    }
}