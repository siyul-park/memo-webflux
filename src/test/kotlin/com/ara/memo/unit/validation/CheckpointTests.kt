package com.ara.memo.unit.validation

import com.ara.memo.mocker.ValidateObjectMocker
import com.ara.memo.unit.UnitTests
import com.ara.memo.util.validation.Checkpoint
import com.ara.memo.util.validation.exception.ValidationException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

class CheckpointTests @Autowired constructor(
    private val validator: Checkpoint
) : UnitTests() {
    @Test
    fun testValidate() {
        var validateObjectMocker = ValidateObjectMocker(null, 10)
        assertThrows<ValidationException> { validator.validate(validateObjectMocker).block() }

        validateObjectMocker = ValidateObjectMocker(10, null)
        validator.validate(validateObjectMocker).doOnSuccess {
            assertEquals(it, validateObjectMocker)
        }.block()
    }
}