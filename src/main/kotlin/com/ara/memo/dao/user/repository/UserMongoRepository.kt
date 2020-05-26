package com.ara.memo.dao.user.repository

import com.ara.memo.entity.user.User
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

@Profile("!test")
interface UserMongoRepository : UserRepository, ReactiveMongoRepository<User, String> {
    override fun findByUsername(username: String): Mono<User>

    override fun existsByUsername(username: String): Mono<Boolean>
}
