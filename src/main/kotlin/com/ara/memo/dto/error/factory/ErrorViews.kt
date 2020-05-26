package com.ara.memo.dto.error.factory

import com.ara.memo.util.cache.WeakCache

object ErrorViews : ErrorViewFactory by CachedErrorViewFactory(WeakCache(), WeakCache())