package com.ara.memo.database.repository

import com.ara.memo.database.entity.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveMongoRepository<User, String> {
    fun existsByUsername(username: String): Mono<Boolean>
}