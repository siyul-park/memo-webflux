package com.ara.memo.router

import com.ara.memo.handler.HelloHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class HelloRouterConfig(private val handler: HelloHandler) {
    @Bean
    fun helloRouter() = router {
        GET("/hello", handler::hello)
    }
}