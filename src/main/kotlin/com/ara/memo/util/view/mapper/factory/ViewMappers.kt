package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.factory.Caches
import com.google.common.cache.CacheBuilder
import java.time.Duration

object ViewMappers : ViewMapperFactory by CachedViewMapperFactory(
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