package com.modzo.eventstore.domain.event.commands.add

import com.modzo.eventstore.domain.event.Event
import com.modzo.eventstore.domain.event.Events
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AddEventHandler {
    private final Events events
    private final AddEventValidator validator

    @Autowired
    AddEventHandler(Events events, AddEventValidator validator) {
        this.events = events
        this.validator = validator
    }

    @Transactional
    void handle(AddEvent addEvent) {
        validator.validate(addEvent)

        Event event = new Event(
                uniqueId: addEvent.uniqueId,
                type: addEvent.type,
                data: addEvent.data

        )
        events.save(event)
    }
}
