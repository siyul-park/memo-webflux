package com.ara.memo.config

import com.ara.memo.jackson.JacksonModule
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfiguration(
    private val jacksonModule: JacksonModule
) {
    @Autowired(required = true)
    fun configObjectMapper(objectMapper: ObjectMapper) {
        objectMapper.apply {
            registerModule(Jdk8Module())
            registerModule(jacksonModule)
            propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
        }
    }
}