package com.ara.memo.view.user

import com.ara.memo.view.View
import com.fasterxml.jackson.annotation.JsonView
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

open class UserView(
    @JsonView(IdScope::class)
    @Null(groups = [IdScope::class])
    val id: String?,
    @JsonView(UsernameScope::class)
    @NotNull(groups = [UsernameScope::class])
    val username: String?,
    @JsonView(PasswordScope::class)
    @NotNull(groups = [PasswordScope::class])
    val password: String?
) : View() {
    interface ModifiableScope : UsernameScope, PasswordScope
    interface PublicScope : IdScope, UsernameScope

    interface IdScope
    interface UsernameScope
    interface PasswordScope
}