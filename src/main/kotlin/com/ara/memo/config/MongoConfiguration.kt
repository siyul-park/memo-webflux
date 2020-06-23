package com.ara.memo.config

import com.ara.memo.config.property.MongoProperty
import com.mongodb.ConnectionString
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableConfigurationProperties(MongoProperty::class)
@EnableReactiveMongoRepositories(basePackages = ["com.ara.memo"])
class MongoConfiguration(
    private val property: MongoProperty
) : AbstractReactiveMongoConfiguration() {
    override fun getDatabaseName() = property.database

    override fun reactiveMongoClient(): MongoClient = mongoClient()

    @Bean
    override fun reactiveMongoTemplate() = ReactiveMongoTemplate(mongoClient(), databaseName)

    @Bean
    fun mongoClient(): MongoClient = MongoClients.create(ConnectionString(property.uri))
}