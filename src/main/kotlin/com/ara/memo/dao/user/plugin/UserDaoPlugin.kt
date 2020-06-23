package com.ara.memo.dao.user.plugin

import com.ara.memo.dao.plugin.JacksonExceptionMappingPlugin
import com.ara.memo.dao.plugin.SpringExceptionMappingPlugin
import com.ara.memo.util.plugin.Plugin
import com.ara.memo.util.plugin.PluginManager
import com.ara.memo.util.plugin.ScheduleSubscribePlugin
import org.springframework.stereotype.Component
import reactor.core.scheduler.Scheduler

@Component
class UserDaoPlugin(
    scheduler: Scheduler
) : Plugin by PluginManager()
    .addPlugin(ScheduleSubscribePlugin(scheduler))
    .addPlugin(SpringExceptionMappingPlugin)
    .addPlugin(JacksonExceptionMappingPlugin)
    .addPlugin(UserExceptionMappingPlugin)