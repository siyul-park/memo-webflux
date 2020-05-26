package com.ara.memo.util.view.mapper.factory

class CachedViewMapperFactory : ViewMapperFactory by ViewMapperFactoryFacade(
    CachedRequestViewMapperFactory(),
    CachedResponseViewMapperFactory()
)