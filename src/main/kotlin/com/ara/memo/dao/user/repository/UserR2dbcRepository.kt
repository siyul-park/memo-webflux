package com.ara.memo.dao.user.repository

import com.ara.memo.entity.user.User
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.Query
import reactor.core.publisher.Mono

@Profile("test")
interface UserR2dbcRepository : UserRepository {
    @Query("SELECT * FROM users WHERE username = :username")
    override fun findByUsername(username: String): Mono<User>

    @Query("SELECT COUNT(*) > 0 FROM users WHERE username = :username")
    override fun existsByUsername(username: String): Mono<Boolean>
}
