package com.modzo.eventstore.api.events.add

import com.modzo.eventstore.domain.event.commands.add.AddEvent
import org.hibernate.validator.constraints.NotBlank

class AddEventRequest {

    @NotBlank
    String uniqueId

    @NotBlank
    String type

    @NotBlank
    String data

    AddEvent toAddEvent() {
        new AddEvent(
                uniqueId: uniqueId,
                type: type,
                data: data
        )
    }
}
