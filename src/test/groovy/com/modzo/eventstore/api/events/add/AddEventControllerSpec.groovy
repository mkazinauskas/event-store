package com.modzo.eventstore.api.events.add

import com.modzo.eventstore.domain.event.Event
import com.modzo.eventstore.domain.event.Events
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
class AddEventControllerSpec extends Specification {

    @Autowired
    private TestRestTemplate restTemplate

    @Autowired
    private Events events

    def 'should add new event'() {
        given:
            def request = [
                    id   : randomAlphanumeric(40),
                    topic: 'topic',
                    value: 'value'
            ]
        when:
            ResponseEntity<String> response = restTemplate.postForEntity('/events', request, String)
        then:
            response.statusCode == HttpStatus.CREATED
            response.body == null
            response.headers.getLocation().path.startsWith("/events/${request.id}")

            String uniqueId = response.headers.getLocation().path.split('/').last()

            Event event = events.findOne(uniqueId).get()
            event.id == request.id
            event.created != null
            event.topic == request.topic
            event.value == request.value
    }

}
