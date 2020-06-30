package com.ara.memo.jackson.model

class Field(
    val name: String,
    val children: Fields = Fields(setOf())
)