package com.ara.memo.util.view.mapper.factory

import com.ara.memo.util.cache.Cache
import com.ara.memo.util.view.mapper.MappingInfo
import com.ara.memo.util.view.mapper.RequestViewMapper
import com.ara.memo.util.view.mapper.ResponseViewMapper
import org.springframework.web.reactive.function.server.ServerResponse

class CachedViewMapperFactory(
    requestCache: Cache<MappingInfo<*, *>, RequestViewMapper<*, *>>,
    responseCache: Cache<Pair<MappingInfo<*, *>, () -> ServerResponse.BodyBuilder>, ResponseViewMapper<*, *>>
) : ViewMapperFactory by ViewMapperFactoryFacade(
    CachedRequestViewMapperFactory(requestCache),
    CachedResponseViewMapperFactory(responseCache)
)