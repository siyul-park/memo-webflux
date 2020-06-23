package com.ara.memo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.scheduler.Schedulers

@Configuration
class ReactiveConfiguration {
    @Bean
    fun scheduler() = Schedulers.elastic()
}