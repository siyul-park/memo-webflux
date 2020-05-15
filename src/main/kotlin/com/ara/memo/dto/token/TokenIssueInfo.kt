package com.ara.memo.dto.token

import com.ara.memo.entity.token.Token
import com.ara.memo.entity.token.Type

class TokenIssueInfo(
    override val type: Type,
    override val expiresIn: Int,
    override val id: String? = null
) : Token