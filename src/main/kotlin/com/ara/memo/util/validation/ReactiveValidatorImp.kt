package com.ara.memo.util.validation

import org.springframework.stereotype.Component
import javax.validation.Validator

@Component
class ReactiveValidatorImp(
    validator: Validator
) : ReactiveValidator by ReactiveValidatorAdapter(validator)