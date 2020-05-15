package com.ara.memo.dao.user

import com.ara.memo.entity.user.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveMongoRepository<User, String> {
    fun findByUsername(username: String): Mono<User>

    fun existsByUsername(username: String): Mono<Boolean>
}