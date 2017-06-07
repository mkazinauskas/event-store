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

    ResponseEntity<String> retrieveEvents() {
        return requestTemplate.get('/events')
    }

    ResponseEntity<String> retrieveEvent(String uniqueId) {
        return requestTemplate.get("/events/${uniqueId}")
    }

    ResponseEntity<String> retrieveNextEvent(String uniqueId) {
        return requestTemplate.get("/events/next?uniqueId=${uniqueId}")
    }
}
