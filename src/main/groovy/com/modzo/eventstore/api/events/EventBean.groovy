package com.modzo.eventstore.api.events

import com.modzo.eventstore.domain.event.Event

class EventBean {
    final String uniqueId
    final Date created
    final String topic
    final String value

    EventBean(String uniqueId, Date created, String topic, String value) {
        this.uniqueId = uniqueId
        this.created = created
        this.topic = topic
        this.value = value
    }

    EventBean(Event event) {
        this.uniqueId = event.id
        this.created = event.created
        this.topic = event.topic
        this.value = event.value
    }
}
