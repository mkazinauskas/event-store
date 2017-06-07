package com.modzo.eventstore.api.utils

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class TestContext {

    private final RequestTemplate requestTemplate

    TestContext(RequestTemplate requestTemplate) {
        this.requestTemplate = requestTemplate
    }

    ResponseEntity<String> createEvent(Map<String, String> request) {
        return requestTemplate.post('/events', request)
    }
}
