package com.ara.memo.config

import com.ara.memo.config.property.URIProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(URIProperty::class)
class URIConfig(
    private val property: URIProperty
) {
    val self = property.self
}