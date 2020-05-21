package com.ara.memo.dao.user.repository

import com.ara.memo.entity.user.User
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

@NoRepositoryBean
interface UserRepository : ReactiveCrudRepository<User, String> {
    fun findByUsername(username: String): Mono<User>

    fun existsByUsername(username: String): Mono<Boolean>
}
