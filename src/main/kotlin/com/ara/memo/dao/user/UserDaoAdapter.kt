package com.ara.memo.dao.user

import com.ara.memo.dao.AppliedReactiveDao
import com.ara.memo.dao.ReactiveDao
import com.ara.memo.dao.ReactiveDaoAdapter
import com.ara.memo.dao.user.plugin.UserDaoPlugin
import com.ara.memo.dao.user.repository.UserRepository
import com.ara.memo.entity.user.User
import com.ara.memo.util.plugin.apply
import org.springframework.stereotype.Repository

@Repository
class UserDaoAdapter(
    private val repository: UserRepository,
    private val plugin: UserDaoPlugin
) : UserDao, ReactiveDao<User, String> by AppliedReactiveDao(
    ReactiveDaoAdapter(repository),
    plugin
) {
    override fun findByUsername(username: String) = repository.findByUsername(username).apply(plugin)

    override fun existsByUsername(username: String) = repository.existsByUsername(username).apply(plugin)

    override fun deleteByUsername(username: String) = repository.deleteByUsername(username).apply(plugin).map { }
}