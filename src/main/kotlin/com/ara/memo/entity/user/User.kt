package com.ara.memo.entity.user

import com.ara.memo.entity.Entity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class User(
    @Indexed(unique = true)
    var username: String,
    var password: String,
    @Id
    override val id: String? = null
): Entity<String>