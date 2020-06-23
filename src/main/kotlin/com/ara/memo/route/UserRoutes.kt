package com.ara.memo.route

import com.ara.memo.handler.user.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class UserRoutes(private val handler: UserHandler) {
    @Bean
    fun userRouter() = router {
        (accept(APPLICATION_JSON) and "/users").nest {
            POST("", handler::create)
            PATCH("/{userId}", handler::updateById)
            PATCH("/@{username}", handler::updateByName)
            GET("", handler::readAll)
            GET("/{userId}", handler::readById)
            DELETE("", handler::deleteAll)
            DELETE("/{userId}", handler::deleteById)
        }
    }
}