package com.modzo.eventstore.api.events.add

import com.modzo.eventstore.api.ApiSpec
import com.modzo.eventstore.api.Error
import com.modzo.eventstore.api.utils.DummyEvent
import com.modzo.eventstore.domain.event.Event
import com.modzo.eventstore.domain.event.Events
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class AddEventControllerSpec extends ApiSpec {

    @Autowired
    private Events events

    @Autowired
    private DummyEvent dummyEvent

    def 'should add new event'() {
        given:
            def request = dummyEvent.sampleRequest()
        when:
            ResponseEntity<String> response = testContext.createEvent(request, String)
        then:
            response.statusCode == HttpStatus.CREATED
            response.body == null
            response.headers.getLocation().path.endsWith("events/${request.uniqueId}")
        and:
            String uniqueId = response.headers.getLocation().path.split('/').last()

            Event event = events.findByUniqueId(uniqueId).get()
            event.uniqueId == request.uniqueId
            event.type == request.type
            event.data == request.data
    }

    def 'should fail to add new event with validation error'() {
        given:
            def request = dummyEvent.sampleRequest()
            request.uniqueId = null
        when:
            ResponseEntity<String> response = testContext.createEvent(request, String)
        then:
            response.statusCode == HttpStatus.BAD_REQUEST
        and:
            def body = new JsonSlurper().parseText(response.body)
            body.errors.size() == 1
            with(body.errors.first()) { error ->
                error.field == 'uniqueId'
                error.rejectedValue == null
                error.code == 'NotBlank'
                error.defaultMessage == 'may not be empty'
            }
    }

    def 'should fail to add new event'() {
        given:
            def request = dummyEvent.sampleRequest()
            dummyEvent.create(request)
        when:
            ResponseEntity<Error> response = testContext.createEvent(request, Error)
        then:
            response.statusCode == HttpStatus.BAD_REQUEST
        and:
            response.body.id == 'EVENT_UNIQUE_ID_EXISTS'
            response.body.message == 'Event unique id already exists'
    }
}
