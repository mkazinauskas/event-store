package com.modzo.eventstore.api.utils

import com.modzo.eventstore.api.events.add.AddEventRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class TestContext {

    private final RequestTemplate requestTemplate

    TestContext(RequestTemplate requestTemplate) {
        this.requestTemplate = requestTemplate
    }

    def <T> ResponseEntity<T> createEvent(AddEventRequest request, Class<T> clazz) {
        return requestTemplate.post('/events', asMap(request), clazz)
    }

    def <T>ResponseEntity<T> retrieveNextEvent(long id, Class<T> clazz) {
        return requestTemplate.get("/events/next?id=${id}", clazz)
    }

    def <T>ResponseEntity<T> retrieveFirstEvent(Class<T> clazz) {
        return requestTemplate.get('/events/next', clazz)
    }

    private static Map asMap(AddEventRequest request) {
        request.class.declaredFields
                .findAll { !it.synthetic }
                .collectEntries {
            [(it.name): request."$it.name"]
        }
    }
}
