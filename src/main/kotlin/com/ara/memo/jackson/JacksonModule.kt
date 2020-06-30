package com.ara.memo.jackson

import com.ara.memo.jackson.model.JsonProjection
import com.ara.memo.jackson.model.JsonView
import com.ara.memo.jackson.serialization.JsonProjectionSerializer
import com.ara.memo.jackson.serialization.JsonViewSerializer
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.stereotype.Component

@Component
class JacksonModule(
    jsonViewSerializer: JsonViewSerializer,
    jsonProjectionSerializer: JsonProjectionSerializer
) : SimpleModule() {
    init {
        this.addSerializer(JsonView::class.java, jsonViewSerializer)
        this.addSerializer(JsonProjection::class.java, jsonProjectionSerializer)
    }
}
