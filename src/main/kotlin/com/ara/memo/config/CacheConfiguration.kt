package com.ara.memo.config

import com.ara.memo.util.cache.factory.GuavaCacheFactory
import org.springframework.cache.CacheManager
import org.springframework.cache.guava.GuavaCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CacheConfiguration {
    @Bean
    fun cacheManager(): CacheManager {
        val simpleCacheManager = SimpleCacheManager()
        val dynamicCache = GuavaCache(KEY_DYNAMIC,  GuavaCacheFactory.dynamic())

        simpleCacheManager.setCaches(listOf(dynamicCache))

        return simpleCacheManager
    }

    companion object {
        const val KEY_DYNAMIC = "dynamic"
    }
}