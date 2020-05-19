package com.ara.memo.mocker

import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

class ValidateObjectMocker(
    @NotNull val p0: Int?,
    @Null val p1: Int?
)