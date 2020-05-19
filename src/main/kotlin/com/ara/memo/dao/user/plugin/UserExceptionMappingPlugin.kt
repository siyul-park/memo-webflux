package com.ara.memo.dao.user.plugin

import com.ara.memo.dao.user.mapper.UserExceptionMapper
import com.ara.memo.util.plugin.ExceptionMappingPlugin

object UserExceptionMappingPlugin : ExceptionMappingPlugin(UserExceptionMapper)