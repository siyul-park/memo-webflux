package com.ara.memo.util.error

interface SingleError : Error {
    val message: String?
}
