package com.ara.meno.integration

import com.ara.meno.handler.HelloHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class HelloRouter(private val handler: HelloHandler) {
    @Bean
    fun route() = router { GET("/hello", handler::hello) }
}