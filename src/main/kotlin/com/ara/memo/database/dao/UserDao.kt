package com.ara.memo.database.dao

import com.ara.memo.database.entity.User
import com.ara.memo.database.repository.ReactiveDaoAdapter
import com.ara.memo.database.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserDao(repository: UserRepository) : ReactiveDaoAdapter<User, String>(repository)