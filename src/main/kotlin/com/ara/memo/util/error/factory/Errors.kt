package com.ara.memo.util.error.factory

import com.ara.memo.util.cache.factory.Caches
import com.google.common.cache.CacheBuilder
import java.time.Duration

object Errors : ErrorFactory by CachedErrorFactory(
    Caches.from(
        CacheBuilder.newBuilder()
            .softValues()
            .expireAfterAccess(Duration.ofHours(1))
            .build()
    ),
    Caches.from(
        CacheBuilder.newBuilder()
            .softValues()
            .expireAfterAccess(Duration.ofHours(1))
            .build()
    ),
    Caches.from(
        CacheBuilder.newBuilder()
            .softValues()
            .expireAfterAccess(Duration.ofHours(1))
            .build()
    )
)