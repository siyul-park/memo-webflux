package com.ara.memo.dao.user

import com.ara.memo.dao.ReactiveDao
import com.ara.memo.entity.user.User
import reactor.core.publisher.Mono

interface UserDao: ReactiveDao<User, String> {
    fun findByUsername(username: String): Mono<User>

    fun existsByUsername(username: String): Mono<Boolean>

    fun deleteByUsername(username: String): Mono<Unit>
}