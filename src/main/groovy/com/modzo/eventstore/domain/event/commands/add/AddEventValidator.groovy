package com.modzo.eventstore.domain.event.commands.add

import com.modzo.eventstore.domain.DomainException
import org.apache.commons.lang.StringUtils

class AddEventValidator {

    void validate(AddEvent addEvent) {
        if (StringUtils.isBlank(addEvent.id)) {
            throw new DomainException('EVENT_ID', 'Event id cannot be blank')
        }

        if (StringUtils.isBlank(addEvent.topic)) {
            throw new DomainException('EVENT_TOPIC', 'Topic cannot be blank')
        }

        if (StringUtils.isBlank(addEvent.value)) {
            throw new DomainException('EVENT_VALUE', 'Value cannot be blank')
        }
    }
}
