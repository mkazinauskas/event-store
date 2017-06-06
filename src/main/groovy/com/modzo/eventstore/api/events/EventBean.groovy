package com.modzo.eventstore.api.events

import com.modzo.eventstore.domain.event.Event

class EventBean {
    final String id
    final Date created
    final String topic
    final String value

    EventBean(String id, Date created, String topic, String value) {
        this.id = id
        this.created = created
        this.topic = topic
        this.value = value
    }

    EventBean(Event event) {
        this.id = event.id
        this.created = event.created
        this.topic = event.topic
        this.value = event.value
    }
}
