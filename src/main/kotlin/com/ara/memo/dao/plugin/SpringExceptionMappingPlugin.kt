package com.ara.memo.dao.plugin

import com.ara.memo.dao.mapper.SpringExceptionMapper

object SpringExceptionMappingPlugin : ExceptionMappingPlugin(SpringExceptionMapper)