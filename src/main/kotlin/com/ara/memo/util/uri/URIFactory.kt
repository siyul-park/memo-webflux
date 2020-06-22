package com.ara.memo.util.uri

import com.ara.memo.config.URIConfig
import org.springframework.stereotype.Component
import java.net.URI

@Component
class URIFactory(
    private val config: URIConfig
) {
    fun <T> create(resource: String, id: T) = URI.create("${config.self}/${resource}/${id}")
}