package com.modzo.eventstore.api.events

import com.modzo.eventstore.api.ApiSpec
import com.modzo.eventstore.api.utils.DummyEvent
import com.modzo.eventstore.domain.event.Event
import com.modzo.eventstore.domain.event.Events
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class EventsControllerSpec extends ApiSpec {

    @Autowired
    private Events events

    @Autowired
    private DummyEvent dummyEvent

    def 'should find next saved event when event id not provided'() {
        given:
            dummyEvent.create()
        and:
            Event firstEvent = events.findOne(1L)
        when:
            ResponseEntity<EventBean> response = testContext.retrieveNextEvent(0)
        then:
            response.statusCode == HttpStatus.OK

            def retrievedEvent = response.body
            retrievedEvent.uniqueId == firstEvent.uniqueId
            retrievedEvent.type == firstEvent.type
            retrievedEvent.data == firstEvent.data
    }

    def 'should find next saved event when event id provided'() {
        given:
            def firstEvent = dummyEvent.sampleRequest()
            dummyEvent.create(firstEvent)
            long firstEventId = events.findByUniqueId(firstEvent.uniqueId).get().id
        and:
            def secondEvent = dummyEvent.sampleRequest()
            dummyEvent.create(secondEvent)
        when:
            ResponseEntity<EventBean> response = testContext.retrieveNextEvent(firstEventId)
        then:
            response.statusCode == HttpStatus.OK
            def retrievedEvent = response.body

            retrievedEvent.uniqueId == secondEvent.uniqueId
            retrievedEvent.type == secondEvent.type
            retrievedEvent.data == secondEvent.data
    }
}
