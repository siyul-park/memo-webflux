package com.ara.memo.handler

import com.ara.memo.view.error.ErrorView
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.result.view.ViewResolver
import reactor.core.publisher.Mono

@Component
@Order(-2)
class ErrorWebExceptionHandler(
    resourceProperties: ResourceProperties,
    applicationContext: ApplicationContext,
    viewResolversProvider: ObjectProvider<List<ViewResolver>>,
    serverCodecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(GlobalErrorAttributes(), resourceProperties, applicationContext) {
    init {
        setViewResolvers(viewResolversProvider.getIfAvailable { emptyList() })
        setMessageWriters(serverCodecConfigurer.writers)
        setMessageReaders(serverCodecConfigurer.readers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse?> {
        return RouterFunctions.route(
            RequestPredicates.all(), HandlerFunction { request: ServerRequest -> renderErrorResponse(request) })
    }

    private fun renderErrorResponse(request: ServerRequest): Mono<ServerResponse?> {
        val errorPropertiesMap = getErrorAttributes(request, false)

        return ServerResponse.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(
                when (isServerError(errorPropertiesMap["status"] as Int)) {
                    true -> ErrorView(
                        errorPropertiesMap["error"] as String,
                        errorPropertiesMap["path"] as String,
                        errorPropertiesMap["error"] as String
                    )
                    false -> ErrorView(
                        errorPropertiesMap["name"] as String,
                        errorPropertiesMap["path"] as String,
                        errorPropertiesMap["message"] as String
                    )
                }
            ))
    }

    private fun isServerError(status: Int) = status in 500..599
}