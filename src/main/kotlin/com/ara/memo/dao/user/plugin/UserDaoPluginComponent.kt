package com.ara.memo.dao.user.plugin

import com.ara.memo.dao.plugin.SpringExceptionMappingPlugin
import com.ara.memo.util.plugin.PluginManager
import com.ara.memo.util.plugin.ScheduleSubscribePlugin
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler

@Component
class UserDaoPluginComponent(
    scheduler: Scheduler
) : UserDaoPlugin {
    private val pluginManager = PluginManager()

    init {
        pluginManager
            .addPlugin(ScheduleSubscribePlugin(scheduler))
            .addPlugin(SpringExceptionMappingPlugin)
            .addPlugin(UserExceptionMappingPlugin)
    }

    override fun <T> apply(mono: Mono<T>) = pluginManager.apply(mono)

    override fun <T> apply(flux: Flux<T>) = pluginManager.apply(flux)
}