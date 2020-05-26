package com.ara.memo.util.error.factory

import com.ara.memo.util.cache.WeakCache

object Errors : ErrorFactory by CachedErrorFactory(WeakCache(), WeakCache())