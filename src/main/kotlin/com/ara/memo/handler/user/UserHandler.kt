package com.ara.memo.handler.user

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest

@Component
class UserHandler(
    private val createUserHandler: CreateUserHandler,
    private val readUsersHandler: ReadUsersHandler,
    private val readUserByNameHandler: ReadUserByNameHandler,
    private val readUserByIdHandler: ReadUserByIdHandler,
    private val updateUserByNameHandler: UpdateUserByNameHandler,
    private val updateUserByIdHandler: UpdateUserByIdHandler
) {
    fun create(request: ServerRequest) = createUserHandler.handleRequest(request)

    fun readAll(request: ServerRequest) = readUsersHandler.handleRequest(request)
    fun readByName(request: ServerRequest) = readUserByNameHandler.handleRequest(request)
    fun readById(request: ServerRequest) = readUserByIdHandler.handleRequest(request)

    fun updateByName(request: ServerRequest) = updateUserByNameHandler.handleRequest(request)
    fun updateById(request: ServerRequest) = updateUserByIdHandler.handleRequest(request)
}