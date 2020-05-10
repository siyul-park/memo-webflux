package com.ara.memo.view.user

import com.ara.memo.view.View
import com.fasterxml.jackson.annotation.JsonView

class UserView(
    @JsonView(IdView::class) val id: String?,
    @JsonView(UsernameView::class) val username: String,
    @JsonView(PasswordView::class) val password: String
) : View() {
    interface ModifiableView : UsernameView, PasswordView
    interface PublicView : IdView, UsernameView

    interface IdView
    interface UsernameView
    interface PasswordView
}