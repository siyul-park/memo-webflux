package com.ara.memo.service.user

import com.ara.memo.database.dao.UserDao
import com.ara.memo.database.entity.User
import com.ara.memo.exception.UserAlreadyExistException
import com.ara.memo.exception.UserNotExistException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
    private val dao: UserDao
) {
    fun signUp(user: User): Mono<User> =
        dao.existsByUsername(user.username)
            .flatMap { when (it) {
                true -> Mono.error(UserAlreadyExistException)
                false -> dao.save(user)
            } }

    fun updateById(id: String, updater: User.() -> Unit) = findById(id)
        .switchIfEmpty(Mono.error(UserNotExistException))
        .flatMap { update(it, updater) }

    fun update(user: User, updater: User.() -> Unit) = Mono.fromCallable { user.apply(updater) }
        .flatMap(dao::save)

    fun findAll() = dao.findAll()

    fun findById(id: String) = dao.findById(id)
}