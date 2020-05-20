package com.ara.memo.util.validation.mapper

import com.ara.memo.util.error.Error
import com.ara.memo.util.mapper.Mapper
import javax.validation.ConstraintViolation

interface ConstraintViolationMapper : Mapper<ConstraintViolation<*>, Error>