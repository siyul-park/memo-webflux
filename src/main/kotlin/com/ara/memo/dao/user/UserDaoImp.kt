package com.ara.memo.dao.user

import com.ara.memo.dao.ReactiveDao
import com.ara.memo.dao.ReactiveDaoAdapter
import com.ara.memo.dao.ScheduledDao
import com.ara.memo.entity.User
import org.springframework.stereotype.Repository
import reactor.core.scheduler.Scheduler

@Repository
class UserDaoImp(
    private val repository: UserRepository,
    private val scheduler: Scheduler
): UserDao, ReactiveDao<User, String> by ScheduledDao(
    ReactiveDaoAdapter(repository),
    scheduler
) {
    override fun existsByUsername(username: String)
        = repository.existsByUsername(username).subscribeOn(scheduler)
}