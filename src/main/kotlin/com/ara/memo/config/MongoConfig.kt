package com.ara.memo.config

import com.mongodb.ConnectionString
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(basePackages = ["com.ara.memo"])
class MongoConfig(
    @Value("\${spring.data.mongodb.uri}") private val uri: String,
    @Value("\${spring.data.mongodb.database}") private val database: String
) : AbstractReactiveMongoConfiguration() {
    override fun getDatabaseName() = database

    override fun reactiveMongoClient(): MongoClient = mongoClient()

    @Bean
    override fun reactiveMongoTemplate() = ReactiveMongoTemplate(mongoClient(), databaseName)

    @Bean
    fun mongoClient(): MongoClient = MongoClients.create(ConnectionString(uri))
}