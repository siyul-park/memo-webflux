package com.ara.memo.util.uri

import com.ara.memo.config.URIConfiguration
import org.springframework.stereotype.Component
import java.net.URI

@Component
class URIFactory(
    private val configuration: URIConfiguration
) {
    fun <T> create(resource: String, id: T) = URI.create("${configuration.self}/${resource}/${id}")
}