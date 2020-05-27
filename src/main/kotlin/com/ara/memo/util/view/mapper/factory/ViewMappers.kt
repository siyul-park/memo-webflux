package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.factory.Caches

object ViewMappers : ViewMapperFactory by CachedViewMapperFactory(Caches.dynamic(), Caches.dynamic())