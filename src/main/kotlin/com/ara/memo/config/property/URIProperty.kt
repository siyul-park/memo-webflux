package com.ara.memo.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "uri")
data class URIProperty(
    val self: String = "https://localhost:3000"
)