package com.ara.memo.dao.plugin

import com.ara.memo.dao.mapper.JacksonExceptionMapper
import com.ara.memo.util.plugin.ExceptionMappingPlugin

object JacksonExceptionMappingPlugin : ExceptionMappingPlugin(JacksonExceptionMapper)