package com.ara.memo.entity

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Indexed(unique = true)
    var username: String,
    var password: String,
    override val id: String? = null
): Entity<String>()