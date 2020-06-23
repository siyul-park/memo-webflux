package com.ara.memo.util.value.path.exception

import com.ara.memo.exception.BadRequestException

class CantFindPathVariableException(reason: String?) : BadRequestException(reason)