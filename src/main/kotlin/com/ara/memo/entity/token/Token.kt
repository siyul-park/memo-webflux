package com.ara.memo.entity.token

import com.ara.memo.entity.Entity

interface Token : Entity<String> {
    val type: Type
    val expiresIn: Int
}