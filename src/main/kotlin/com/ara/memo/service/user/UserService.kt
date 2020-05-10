package com.ara.memo.service.user

import com.ara.memo.database.dao.UserDao
import com.ara.memo.database.entity.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val dao: UserDao
) {
    fun create(user: User) = dao.save(user)
}