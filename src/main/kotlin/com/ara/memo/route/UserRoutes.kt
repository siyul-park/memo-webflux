package com.ara.memo.route

import com.ara.memo.handler.UserHandler
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class UserRoutes(
    private val handler: UserHandler
) {
    private val logger = LoggerFactory.getLogger(UserRoutes::class.java)

    @Bean
    fun userRouter() = router {
        (accept(APPLICATION_JSON) and "/users").nest {
            POST("", handler::create)
        }
    }
}