package com.modzo.eventstore.api.events.add

import com.modzo.eventstore.api.ApiSpec
import com.modzo.eventstore.api.utils.DummyEvent
import com.modzo.eventstore.domain.event.Event
import com.modzo.eventstore.domain.event.Events
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
            ResponseEntity<String> response = testContext.createEvent(request)
        then:
            response.statusCode == HttpStatus.CREATED
            response.body == null
            response.headers.getLocation().path.endsWith("events/${request.uniqueId}")
        and:
            String uniqueId = response.headers.getLocation().path.split('/').last()

            Event event = events.findByUniqueId(uniqueId).get()
            event.uniqueId == request.uniqueId
            event.created != null
            event.topic == request.topic
            event.value == request.value
    }

}
