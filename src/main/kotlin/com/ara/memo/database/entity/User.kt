package com.ara.memo.database.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    override val id: String?,
    var username: String,
    var password: String
): Entity<String>()