package com.ara.memo.jackson

import com.ara.memo.jackson.serialization.ViewProjectionSerialization
import com.ara.memo.jackson.serialization.ViewSerialization
import com.ara.memo.util.view.View
import com.ara.memo.util.view.ViewProjection
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.stereotype.Component

@Component
class JacksonModule(
    viewSerialization: ViewSerialization,
    viewProjectionSerialization: ViewProjectionSerialization
) : SimpleModule() {
    init {
        this.addSerializer(View::class.java, viewSerialization)
        this.addSerializer(ViewProjection::class.java, viewProjectionSerialization)
    }
}
