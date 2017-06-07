package com.modzo.eventstore.api.events.add

import com.modzo.eventstore.domain.event.commands.add.AddEvent
import org.hibernate.validator.constraints.NotBlank

class AddEventRequest {

    @NotBlank
    String uniqueId

    @NotBlank
    String topic

    @NotBlank
    String value

    AddEvent toAddEvent() {
        new AddEvent(
                uniqueId: uniqueId,
                topic: topic,
                value: value
        )
    }
}
