package com.ara.memo.util.view.mapper.response.factory

import com.ara.memo.util.cache.factory.Caches
import com.google.common.cache.CacheBuilder
import java.time.Duration

object ResponseViewMappers : ResponseViewMapperFactory by CachedResponseViewMapperFactory(
    Caches.from(
        CacheBuilder.newBuilder()
            .softValues()
            .expireAfterAccess(Duration.ofHours(1))
            .build()
    )
)