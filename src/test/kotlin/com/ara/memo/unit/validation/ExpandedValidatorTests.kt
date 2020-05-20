package com.ara.memo.unit.validation

import com.ara.memo.mocker.ValidateObjectMocker
import com.ara.memo.unit.UnitTests
import com.ara.memo.util.validation.ExpandedValidator
import com.ara.memo.util.validation.exception.ValidationException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

class ExpandedValidatorTests @Autowired constructor(
    private val validator: ExpandedValidator
) : UnitTests() {
    @Test
    fun testValidate() {
        val validateObjectMocker = ValidateObjectMocker(null, 10)
        assertThrows<ValidationException> { validator.validate(validateObjectMocker).block() }
    }
}