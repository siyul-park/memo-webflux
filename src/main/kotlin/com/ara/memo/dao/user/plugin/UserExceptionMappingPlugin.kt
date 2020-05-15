package com.ara.memo.dao.user.plugin

import com.ara.memo.dao.plugin.ExceptionMappingPlugin
import com.ara.memo.dao.user.mapper.UserExceptionMapper

object UserExceptionMappingPlugin : ExceptionMappingPlugin(UserExceptionMapper)