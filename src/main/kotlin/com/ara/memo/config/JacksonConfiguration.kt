package com.ara.memo.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder


@Configuration
class JacksonConfiguration {
    @Bean
    fun objectMapper(): ObjectMapper = Jackson2ObjectMapperBuilder.json()
        .modules(Jdk8Module())
        .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        .build()
}