package com.ara.memo.handler

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.web.reactive.function.server.ServerRequest

class GlobalErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(request: ServerRequest, includeStackTrace: Boolean): Map<String, Any> {
        val error = getError(request)

        return super.getErrorAttributes(request, includeStackTrace).apply {
            set("name", error::class.java.name)
        }
    }
}