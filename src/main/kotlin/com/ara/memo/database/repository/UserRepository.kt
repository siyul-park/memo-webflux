package com.ara.memo.database.repository

import com.ara.memo.database.entity.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository : ReactiveMongoRepository<User, String>