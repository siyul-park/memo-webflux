package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.Cache
import com.ara.memo.util.view.mapper.RequestViewMapper
import com.ara.memo.util.view.mapper.ResponseViewMapper

class CachedViewMapperFactory(
    requestCache: Cache<Any, RequestViewMapper<*, *>>,
    responseCache: Cache<Any, ResponseViewMapper<*, *>>
) : ViewMapperFactory by ViewMapperFactoryFacade(
    CachedRequestViewMapperFactory(requestCache),
    CachedResponseViewMapperFactory(responseCache)
)