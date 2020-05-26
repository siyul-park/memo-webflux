package com.ara.memo.config

import com.ara.memo.config.property.R2dbcProperty
import io.r2dbc.h2.H2ConnectionConfiguration
import io.r2dbc.h2.H2ConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableConfigurationProperties(R2dbcProperty::class)
@EnableR2dbcRepositories(basePackages = ["com.ara.memo"])
class R2dbcConfig(
    private val property: R2dbcProperty
) : AbstractR2dbcConfiguration() {
    @Bean
    override fun connectionFactory(): ConnectionFactory = H2ConnectionFactory(
        H2ConnectionConfiguration.builder().apply {
            url(property.uri)
            property.database?.let { file("./${it}") }
            property.inMemory?.let { inMemory(it) }
            username(property.username)
        }.build()
    )
}
