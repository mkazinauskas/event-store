package com.modzo.eventstore.domain.event.commands.add

import com.modzo.eventstore.domain.event.Event
import com.modzo.eventstore.domain.event.Events
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AddEventHandler {

    @Autowired
    private Events events

    @Autowired
    private AddEventValidator validator

    @Transactional
    void handle(AddEvent addEvent) {
        validator.validate(addEvent)

        Event event = new Event(
                uniqueId: addEvent.uniqueId,
                topic: addEvent.topic,
                value: addEvent.value

        )
        events.save(event)
    }
}
