package com.ara.memo.dto.error.factory

import com.ara.memo.util.cache.factory.Caches

object ErrorViews : ErrorViewFactory by CachedErrorViewFactory(Caches.dynamic(), Caches.dynamic())