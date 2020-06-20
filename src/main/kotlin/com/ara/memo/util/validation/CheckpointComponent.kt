package com.ara.memo.util.validation

import com.ara.memo.util.validation.mapper.ConstraintViolationMapper
import org.springframework.stereotype.Component

@Component
class CheckpointComponent(
    validator: ReactiveValidator,
    constraintViolationMapper: ConstraintViolationMapper
) : Checkpoint by CheckpointAdapter(validator, constraintViolationMapper)