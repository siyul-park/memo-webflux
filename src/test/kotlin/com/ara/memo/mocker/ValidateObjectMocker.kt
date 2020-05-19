package com.ara.memo.mocker

import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

class ValidateObjectMocker(
    @get:NotNull val p0: Int?,
    @get:Null val p1: Int?
)