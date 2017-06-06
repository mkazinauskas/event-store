package com.modzo.eventstore.api.events.add

import com.modzo.eventstore.domain.event.commands.add.AddEvent
import org.hibernate.validator.constraints.NotBlank

class AddEventRequest {

    @NotBlank
    String id

    @NotBlank
    String topic

    @NotBlank
    String value

    AddEvent toAddEvent() {
        new AddEvent().with {
            it.id = id
            it.topic = topic
            it.value = value

            it
        }
    }
}
