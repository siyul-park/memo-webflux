package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.factory.Caches
import com.google.common.cache.CacheBuilder

object ViewMappers : ViewMapperFactory by CachedViewMapperFactory(
    Caches.from(
        CacheBuilder.newBuilder()
            .softValues()
            .build()
    ),
    Caches.from(
        CacheBuilder.newBuilder()
            .softValues()
            .build()
    )
)