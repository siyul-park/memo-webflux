package com.ara.memo.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.DecoderHttpMessageReader
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.util.MimeType

@Configuration
class HttpMessageReaderConfiguration(private val objectMapper: ObjectMapper) {
    @Bean
    fun decoderHttpMessageReader() = DecoderHttpMessageReader(Jackson2JsonDecoder(objectMapper, MimeType("*")))
}