package com.ara.memo.dao.plugin

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class PluginManager : Plugin {
    private val plugins: MutableList<Plugin> = mutableListOf()

    fun addPlugin(plugin: Plugin): PluginManager {
        plugins.add(plugin)
        return this
    }

    override fun <T> apply(mono: Mono<T>): Mono<T> {
        var result = mono
        plugins.forEach {
            result = it.apply(result)
        }
        return result
    }

    override fun <T> apply(flux: Flux<T>): Flux<T> {
        var result = flux
        plugins.forEach {
            result = it.apply(result)
        }
        return result
    }
}
