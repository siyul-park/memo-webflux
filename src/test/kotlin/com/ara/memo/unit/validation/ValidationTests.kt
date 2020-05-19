package com.ara.memo.unit.validation

import com.ara.memo.mocker.ValidateObjectMocker
import com.ara.memo.unit.UnitTests
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import javax.validation.Validator

class ValidationTests @Autowired constructor(
    private val validator: Validator
) : UnitTests() {
    @Test
    fun testValidate() {
        val validateObjectMocker = ValidateObjectMocker(null, 10)
        val result = validator.validate(validateObjectMocker)

        assertEquals(result?.isEmpty(), false)
    }
}