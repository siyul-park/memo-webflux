package com.ara.memo.view.user

import com.ara.memo.database.entity.User
import com.ara.memo.view.Converter

object UserViewConverter : Converter<User, UserView, String> {
    override fun toView(entity: User) = with(entity) {
        UserView(id, username, password)
    }

    override fun toEntity(view: UserView) = with(view) {
        User(id, username!!, password!!)
    }
}