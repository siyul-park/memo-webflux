package com.ara.memo.router

import com.ara.memo.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class UserRouterConfig(private val handler: UserHandler) {
    @Bean
    fun userRouter() = nest(path("/users"), router {
        POST("/", handler::create)
    })
}