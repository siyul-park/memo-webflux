package com.ara.memo.unit.validation

import com.ara.memo.mocker.ValidateObjectMocker
import com.ara.memo.unit.UnitTests
import com.ara.memo.util.validation.ReactiveValidator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ReactiveValidationTests @Autowired constructor(
    private val validator: ReactiveValidator
) : UnitTests() {
    @Test
    fun testValidate() {
        val validateObjectMocker = ValidateObjectMocker(null, 10)
        val result = validator.validate(validateObjectMocker)
            .collectList()
            .block()

        assertEquals(result?.isEmpty(), false)
    }
}