package com.modzo.eventstore.api.events

import com.modzo.eventstore.api.ApiSpec
import com.modzo.eventstore.api.utils.DummyEvent
import com.modzo.eventstore.domain.event.Events
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class EventsControllerSpec extends ApiSpec {

    @Autowired
    private Events events

    @Autowired
    private DummyEvent dummyEvent

    def 'should list all events'() {
        given:
            def savedEvent = dummyEvent.sampleRequest()
            dummyEvent.create(savedEvent)
        when:
            ResponseEntity<String> response = testContext.retrieveEvents()
        then:
            response.statusCode == HttpStatus.OK

            def body = new JsonSlurper().parseText(response.body)
            def retrievedEvent = body.content.find { it.uniqueId == savedEvent.uniqueId }

            checkSavedEventWithRetrieved(savedEvent, retrievedEvent)
    }

    def 'should find event by unique id'() {
        given:
            def savedEvent = dummyEvent.sampleRequest()
            dummyEvent.create(savedEvent)
        when:
            ResponseEntity<String> response = testContext.retrieveEvent(savedEvent.uniqueId)
        then:
            response.statusCode == HttpStatus.OK

            def retrievedEvent = new JsonSlurper().parseText(response.body)
            checkSavedEventWithRetrieved(savedEvent, retrievedEvent)
    }

    def 'should find next saved event'() {
        given:
            def firstEvent = dummyEvent.sampleRequest()
            dummyEvent.create(firstEvent)
        and:
            def secondEvent = dummyEvent.sampleRequest()
            dummyEvent.create(secondEvent)
        when:
            ResponseEntity<String> response = testContext.retrieveNextEvent(firstEvent.uniqueId)
        then:
            response.statusCode == HttpStatus.OK

            def retrievedEvent = new JsonSlurper().parseText(response.body)
            checkSavedEventWithRetrieved(secondEvent, retrievedEvent)
    }

    private static void checkSavedEventWithRetrieved(def savedEvent, retrievedEvent) {
        assert retrievedEvent.uniqueId == savedEvent.uniqueId
        assert retrievedEvent.created != null
        assert retrievedEvent.topic == savedEvent.topic
        assert retrievedEvent.value == savedEvent.value
    }

}
