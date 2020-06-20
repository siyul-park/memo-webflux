package com.ara.memo.util.view.mapper.request.factory

import com.ara.memo.util.cache.factory.Caches
import com.google.common.cache.CacheBuilder
import java.time.Duration

object RequestViewMappers : RequestViewMapperFactory by CachedRequestViewMapperFactory(
    Caches.from(
        CacheBuilder.newBuilder()
            .softValues()
            .expireAfterAccess(Duration.ofHours(1))
            .build()
    )
)