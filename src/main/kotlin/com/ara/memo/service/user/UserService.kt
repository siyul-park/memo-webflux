package com.ara.memo.service.user

import com.ara.memo.dao.user.UserDao
import com.ara.memo.entity.User
import com.ara.memo.exception.UserAlreadyExistException
import com.ara.memo.exception.UserNotExistException
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
    private val dao: UserDao
) {
    fun create(user: User): Mono<User>
        = dao.save(user)
            .onErrorMap { when (it) {
                is DuplicateKeyException -> UserAlreadyExistException
                else -> it
            } }

    fun updateById(id: String, updater: User.() -> Unit) =
        findById(id)
            .switchIfEmpty(Mono.error(UserNotExistException))
            .flatMap { update(it, updater) }

    fun update(user: User, updater: User.() -> Unit) =
        Mono.fromCallable { user.apply(updater) }
            .flatMap { dao.save(it) }

    fun deleteByIdWhenExist(id: String) =
        dao.existsById(id)
            .flatMap { when (it) {
                true -> dao.deleteById(id)
                false -> Mono.error(UserNotExistException)
            } }

    fun existsById(id: String) = dao.existsById(id)

    fun deleteById(id: String) = dao.deleteById(id)

    fun findAll() = dao.findAll()

    fun findById(id: String) = dao.findById(id)
}