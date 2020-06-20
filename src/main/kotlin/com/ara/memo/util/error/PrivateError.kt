package com.ara.memo.util.error

class PrivateError(
    val privateMessage: String?,
    override val message: String?
) : SingleError
