package com.ara.memo.mocker

import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

class ValidateObjectMocker(
    @field:NotNull val p0: Int?,
    @field:Null val p1: Int?
)