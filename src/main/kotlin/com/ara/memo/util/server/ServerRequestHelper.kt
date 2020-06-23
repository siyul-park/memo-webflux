package com.ara.memo.util.server

import org.springframework.web.reactive.function.server.ServerRequest
import java.util.concurrent.ConcurrentHashMap

fun ServerRequest.hint(key: String, value: Any) = with(ServerRequestHintHelper(this)) {
    hint(key, value)
}

class ServerRequestHintHelper(
    private val serverRequest: ServerRequest
) : ServerRequest by serverRequest {
    private val hints: MutableMap<String, Any> = ConcurrentHashMap()

    fun hint(key: String, value: Any): ServerRequestHintHelper {
        hints[key] = value
        return this
    }
}