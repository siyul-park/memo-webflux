package com.ara.memo.service.token

import com.ara.memo.dao.user.UserDao
import com.ara.memo.dto.token.TokenIssueInfo
import com.ara.memo.entity.token.Token
import com.ara.memo.entity.user.User
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

interface TokenFactory {
    fun create()
}