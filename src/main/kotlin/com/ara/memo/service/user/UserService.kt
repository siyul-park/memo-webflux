package com.ara.memo.service.user

import com.ara.memo.dao.user.UserDao
import com.ara.memo.dto.user.UserAuthorizeInfo
import com.ara.memo.entity.user.User
import com.ara.memo.service.user.exception.UserCantAuthorizeException
import com.ara.memo.service.user.exception.UserNotExistException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
    private val dao: UserDao
) {
    fun authorize(userAuthorizeInfo: UserAuthorizeInfo) : Mono<User>
        = findByUsername(userAuthorizeInfo.username)
        .flatMap { when (it.password == userAuthorizeInfo.password) {
            true -> Mono.just(it)
            false -> Mono.error(UserCantAuthorizeException)
        } }

    fun create(user: User): Mono<User>
        = dao.save(user)

    fun updateById(id: String, updater: User.() -> Unit) : Mono<User>
        = findById(id)
        .flatMap { update(it, updater) }

    fun update(user: User, updater: User.() -> Unit) : Mono<User>
        = Mono.fromCallable { user.apply(updater) }
        .flatMap { dao.save(it) }

    fun deleteByIdWhenExist(id: String) : Mono<Unit>
        = dao.existsById(id)
        .flatMap { when (it) {
            true -> dao.deleteById(id)
            false -> Mono.error(UserNotExistException)
        } }.map {  }

    fun existsById(id: String) = dao.existsById(id)

    fun deleteById(id: String) = dao.deleteById(id)

    fun findAll() = dao.findAll()

    fun findById(id: String) : Mono<User>
        = dao.findById(id)
        .switchIfEmpty(Mono.error(UserNotExistException))

    fun findByUsername(username: String) : Mono<User>
        = dao.findByUsername(username)
        .switchIfEmpty(Mono.error(UserNotExistException))
}