package com.modzo.eventstore.domain.event.commands.add

import com.modzo.eventstore.domain.DomainException
import org.apache.commons.lang.StringUtils
import org.springframework.stereotype.Component

@Component
class AddEventValidator {

    void validate(AddEvent addEvent) {
        if (StringUtils.isBlank(addEvent.uniqueId)) {
            throw new DomainException('EVENT_UNIQUE_ID', 'Event unique id cannot be blank')
        }

        if (StringUtils.isBlank(addEvent.topic)) {
            throw new DomainException('EVENT_TOPIC', 'Topic cannot be blank')
        }

        if (StringUtils.isBlank(addEvent.value)) {
            throw new DomainException('EVENT_VALUE', 'Value cannot be blank')
        }
    }
}
