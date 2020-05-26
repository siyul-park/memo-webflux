package com.ara.memo.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.data.r2dbc")
data class R2dbcProperty(
    val uri: String,
    val database: String? = null,
    val inMemory: String? = null,
    val username: String? = null
)