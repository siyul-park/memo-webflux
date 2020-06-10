package com.ara.memo.util.error

class PrivateError(
    override val message: String?,
    val privateMessage: String?
) : SingleError
