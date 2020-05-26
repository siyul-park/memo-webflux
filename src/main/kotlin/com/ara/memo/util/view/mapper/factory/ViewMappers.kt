package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.WeakCache

object ViewMappers : ViewMapperFactory by CachedViewMapperFactory(WeakCache(), WeakCache())