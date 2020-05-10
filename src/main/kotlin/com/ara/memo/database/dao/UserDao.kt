package com.ara.memo.database.dao

import com.ara.memo.database.entity.User
import com.ara.memo.database.repository.UserRepository
import org.springframework.stereotype.Repository
import reactor.core.scheduler.Scheduler

@Repository
class UserDao(
    private val repository: UserRepository,
    scheduler: Scheduler
) : ScheduledDao<User, String>(
    ReactiveDaoAdapter(repository),
    scheduler
)  {
    fun existsByUsername(username: String) = repository.existsByUsername(username).subscribeOn(scheduler)
}