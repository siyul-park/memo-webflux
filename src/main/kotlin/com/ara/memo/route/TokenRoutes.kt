package com.ara.memo.route

import com.ara.memo.handler.token.TokenHandler
import com.ara.memo.handler.user.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class TokenRoutes(private val handler: TokenHandler) {
    @Bean
    fun tokenRouter() = router {
        (accept(APPLICATION_JSON) and "/token").nest {
            POST("", handler::create)
        }
    }
}