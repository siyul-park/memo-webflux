package com.ara.memo.view.error

import com.ara.memo.view.View

class ErrorView(
    val error: String,
    val path: String,
    val message: String
) : View()