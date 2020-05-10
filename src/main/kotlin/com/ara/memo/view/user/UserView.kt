package com.ara.memo.view.user

import com.ara.memo.database.entity.User
import com.ara.memo.view.View
import com.fasterxml.jackson.annotation.JsonView

class UserView private constructor(
    @JsonView(IdView::class) val id: String?,
    @JsonView(UsernameView::class) val username: String,
    @JsonView(PasswordView::class) val password: String
) : View {
    interface IdView
    interface UsernameView
    interface PasswordView

    interface CreateRequestView : UsernameView, PasswordView
    interface CreateResponseView : IdView, UsernameView, PasswordView

    fun toUser() = User(id, username, password)

    companion object {
        @JvmStatic
        fun from(user: User) = with(user) {
            UserView(id, username, password)
        }
    }
}