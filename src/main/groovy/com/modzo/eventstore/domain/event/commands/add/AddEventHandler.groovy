package com.modzo.eventstore.domain.event.commands.add

import com.modzo.eventstore.domain.event.Event
import com.modzo.eventstore.domain.event.Events
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AddEventHandler {

    @Autowired
    private Events events

    @Autowired
    private AddEventValidator validator

    void handle(AddEvent addEvent) {
        validator.validate(addEvent)

        Event event = new Event(
                id: addEvent.id,
                topic: addEvent.topic,
                value: addEvent.value

        )
        events.save(event)
    }
}
