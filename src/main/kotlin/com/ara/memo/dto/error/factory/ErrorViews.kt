package com.ara.memo.dto.error.factory

import com.ara.memo.util.cache.factory.Caches
import com.google.common.cache.CacheBuilder
import java.time.Duration

object ErrorViews : ErrorViewFactory by CachedErrorViewFactory(
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