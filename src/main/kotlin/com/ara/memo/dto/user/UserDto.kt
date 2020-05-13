package com.ara.memo.dto.user

import com.ara.memo.dto.Dto
import com.ara.memo.entity.User

abstract class UserDto(
    var id: String? = null,
    var username: String? = null,
    var password: String? = null
) : Dto<User, String> {
    override fun toEntity() = User(username!!, password!!, id)
}