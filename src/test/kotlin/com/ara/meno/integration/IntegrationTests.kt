package com.ara.meno.integration

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class IntegrationTests(private val baseUri: String) {
    @Autowired
    protected lateinit var webClient: WebTestClient

    private val uriCache: HashMap<String, String> = hashMapOf()

    protected fun linkUri(uri: String = "") = uriCache.getOrPut(uri) { "${baseUri}${uri}" }
}