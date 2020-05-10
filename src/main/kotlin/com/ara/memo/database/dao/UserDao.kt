package com.ara.memo.database.dao

import com.ara.memo.database.entity.User
import com.ara.memo.database.repository.ReactiveDaoImp
import com.ara.memo.database.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserDao(repository: UserRepository) : ReactiveDaoImp<User, String>(repository)