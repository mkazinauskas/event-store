package com.modzo.eventstore.domain.event.commands.add

import com.modzo.eventstore.domain.DomainException
import com.modzo.eventstore.domain.event.Events
import org.springframework.stereotype.Component

import static org.apache.commons.lang.StringUtils.isBlank

@Component
class AddEventValidator {

    private final Events events

    AddEventValidator(Events events) {
        this.events = events
    }

    void validate(AddEvent addEvent) {
        if (isBlank(addEvent.uniqueId)) {
            throw new DomainException('EVENT_UNIQUE_ID', 'Event unique id cannot be blank')
        }

        if (events.findByUniqueId(addEvent.uniqueId).present) {
            throw new DomainException('EVENT_UNIQUE_ID', 'Event unique id is already saved')
        }

        if (isBlank(addEvent.topic)) {
            throw new DomainException('EVENT_TOPIC', 'Topic cannot be blank')
        }

        if (isBlank(addEvent.value)) {
            throw new DomainException('EVENT_VALUE', 'Value cannot be blank')
        }
    }
}
