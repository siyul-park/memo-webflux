package com.ara.memo.dao.user

import com.ara.memo.entity.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveMongoRepository<User, String> {
    fun existsByUsername(username: String): Mono<Boolean>
}