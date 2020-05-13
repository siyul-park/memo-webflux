package com.ara.memo.dao.user

import com.ara.memo.dao.ReactiveDao
import com.ara.memo.entity.User
import reactor.core.publisher.Mono

interface UserDao: ReactiveDao<User, String> {
    fun existsByUsername(username: String): Mono<Boolean>
}