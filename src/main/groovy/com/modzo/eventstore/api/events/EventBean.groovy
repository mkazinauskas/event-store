package com.modzo.eventstore.api.events

import com.modzo.eventstore.domain.event.Event

class EventBean {
    final long id
    final String uniqueId
    final String type
    final String data

    EventBean() {
        //for deserialization
    }

    EventBean(Event event) {
        this.id = id
        this.uniqueId = event.uniqueId
        this.type = event.type
        this.data = event.data
    }
}
