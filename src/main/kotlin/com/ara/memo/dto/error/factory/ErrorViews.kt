package com.ara.memo.dto.error.factory

import com.ara.memo.util.cache.factory.Caches
import com.ara.memo.util.cache.factory.GuavaCacheFactory

object ErrorViews : ErrorViewFactory by CachedErrorViewFactory(
    Caches.from(GuavaCacheFactory.dynamic()),
    Caches.from(GuavaCacheFactory.dynamic())
)