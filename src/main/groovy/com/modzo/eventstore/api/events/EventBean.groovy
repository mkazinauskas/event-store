package com.modzo.eventstore.api.events

import com.modzo.eventstore.domain.event.Event

class EventBean {
    long id
    String uniqueId
    String type
    String data

    EventBean() {
        //for deserialization
    }

    EventBean(Event event) {
        this.id = event.id
        this.uniqueId = event.uniqueId
        this.type = event.type
        this.data = event.data
    }
}
