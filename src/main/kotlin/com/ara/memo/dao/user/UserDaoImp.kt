package com.ara.memo.dao.user

import com.ara.memo.dao.AppliedReactiveDao
import com.ara.memo.dao.ReactiveDao
import com.ara.memo.dao.ReactiveDaoAdapter
import com.ara.memo.dao.user.plugin.UserDaoPlugin
import com.ara.memo.dao.user.repository.UserRepository
import com.ara.memo.entity.user.User
import org.springframework.stereotype.Repository

@Repository
class UserDaoImp(
    private val repository: UserRepository,
    private val plugin: UserDaoPlugin
): UserDao, ReactiveDao<User, String> by AppliedReactiveDao(
    ReactiveDaoAdapter(repository),
    plugin
) {
    override fun findByUsername(username: String)
        = plugin.apply(repository.findByUsername(username))

    override fun existsByUsername(username: String)
        = plugin.apply(repository.existsByUsername(username))
}