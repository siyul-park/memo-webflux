package com.ara.memo.dao.plugin

import com.ara.memo.dao.mapper.SpringExceptionMapper
import com.ara.memo.util.plugin.ExceptionMappingPlugin

object SpringExceptionMappingPlugin : ExceptionMappingPlugin(SpringExceptionMapper)