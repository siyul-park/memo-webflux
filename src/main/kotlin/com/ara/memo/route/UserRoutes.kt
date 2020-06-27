package com.ara.memo.route

import com.ara.memo.handler.user.UserHandlerFacade
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class UserRoutes(private val handler: UserHandlerFacade) {
    @Bean
    fun userRouter() = router {
        (accept(APPLICATION_JSON) and "/users").nest {
            POST("", handler::create)
            PATCH("/@{username}", handler::updateByName)
            PATCH("/{userId}", handler::updateById)
            GET("", handler::readAll)
            GET("/@{username}", handler::readByName)
            GET("/{userId}", handler::readById)
            DELETE("", handler::deleteAll)
            DELETE("/@{username}", handler::deleteByName)
            DELETE("/{userId}", handler::deleteById)
        }
    }
}