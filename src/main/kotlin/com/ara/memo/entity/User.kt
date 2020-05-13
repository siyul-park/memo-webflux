package com.ara.memo.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    var username: String,
    var password: String,
    override val id: String? = null
): Entity<String>()