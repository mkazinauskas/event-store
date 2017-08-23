package com.modzo.eventstore.domain.event.commands.add

import com.modzo.eventstore.domain.DomainException
import com.modzo.eventstore.domain.event.Events
import org.springframework.stereotype.Component

import static org.apache.commons.lang3.StringUtils.isBlank


@Component
class AddEventValidator {

    private final Events events

    AddEventValidator(Events events) {
        this.events = events
    }

    void validate(AddEvent addEvent) {
        if (isBlank(addEvent.uniqueId)) {
            throw new DomainException('EVENT_UNIQUE_ID_CANNOT_BE_BLANK', 'Event unique id cannot be blank')
        }

        if (events.findByUniqueId(addEvent.uniqueId).present) {
            throw new DomainException('EVENT_UNIQUE_ID_EXISTS', 'Event unique id already exists')
        }

        if (isBlank(addEvent.type)) {
            throw new DomainException('EVENT_TOPIC_CANNOT_BE_BLANK', 'Type cannot be blank')
        }

        if (isBlank(addEvent.data)) {
            throw new DomainException('EVENT_DATA_CANNOT_BE_BLANK', 'Data cannot be blank')
        }
    }
}
