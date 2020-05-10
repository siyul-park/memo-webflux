package com.ara.memo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MemoApplication

fun main(args: Array<String>) {
    runApplication<MemoApplication>(*args)
}
