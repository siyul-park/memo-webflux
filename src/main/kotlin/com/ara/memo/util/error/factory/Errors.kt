package com.ara.memo.util.error.factory

import com.ara.memo.util.cache.factory.Caches
import com.ara.memo.util.cache.factory.GuavaCacheFactory
import com.google.common.cache.CacheBuilder
import java.time.Duration

object Errors : ErrorFactory by CachedErrorFactory(
    Caches.from(GuavaCacheFactory.dynamic()),
    Caches.from(GuavaCacheFactory.dynamic()),
    Caches.from(GuavaCacheFactory.dynamic())
)