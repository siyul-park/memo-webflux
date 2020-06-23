package com.ara.memo.config

import com.ara.memo.util.value.mapper.ValueMappers
import com.ara.memo.util.value.mapper.implement.IntMapper
import com.ara.memo.util.value.mapper.implement.StringMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ValueMapperConfiguration {
    @Bean
    fun valueMappers() = ValueMappers().apply {
        set(Int::class, IntMapper)
        set(String::class, StringMapper)
    }
}