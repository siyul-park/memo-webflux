package com.ara.memo.util.error.factory

import com.ara.memo.util.cache.factory.Caches

object Errors : ErrorFactory by CachedErrorFactory(Caches.create(), Caches.create())