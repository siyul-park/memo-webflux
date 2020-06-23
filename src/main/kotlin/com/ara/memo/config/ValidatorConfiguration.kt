package com.ara.memo.config

import com.ara.memo.util.validation.CheckpointAdapter
import com.ara.memo.util.validation.ReactiveValidator
import com.ara.memo.util.validation.ReactiveValidatorAdapter
import com.ara.memo.util.validation.mapper.ConstraintViolationMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.validation.Validator

@Configuration
class ValidatorConfiguration {
    @Bean
    fun reactiveValidator(validator: Validator) = ReactiveValidatorAdapter(validator)

    @Bean
    fun checkpoint(
        validator: ReactiveValidator,
        constraintViolationMapper: ConstraintViolationMapper
    ) = CheckpointAdapter(validator, constraintViolationMapper)
}