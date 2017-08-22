package com.modzo.eventstore.api.utils

import com.modzo.eventstore.api.events.EventBean
import com.modzo.eventstore.api.events.add.AddEventRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class TestContext {

    private final RequestTemplate requestTemplate

    TestContext(RequestTemplate requestTemplate) {
        this.requestTemplate = requestTemplate
    }

    ResponseEntity<String> createEvent(AddEventRequest request) {
        return requestTemplate.post('/events', asMap(request))
    }

    ResponseEntity<EventBean> retrieveNextEvent(long id) {
        return requestTemplate.get("/events/next?id=${id}")
    }

    private static Map asMap(AddEventRequest request) {
        request.class.declaredFields
                .findAll { !it.synthetic }
                .collectEntries {
            [(it.name): request."$it.name"]
        }
    }
}
