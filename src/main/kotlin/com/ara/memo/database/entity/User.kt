package com.ara.memo.database.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    override val id: String?,
    val username: String,
    val password: String
): Entity<String>()