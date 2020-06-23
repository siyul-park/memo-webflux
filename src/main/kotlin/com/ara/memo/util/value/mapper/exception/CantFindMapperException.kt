package com.ara.memo.util.value.mapper.exception

import com.ara.memo.exception.InternalServerException

class CantFindMapperException(reason: String?) : InternalServerException(reason)