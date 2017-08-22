package com.modzo.eventstore.api.events;

import com.modzo.eventstore.api.ApiException;
import com.modzo.eventstore.domain.event.Event;
import com.modzo.eventstore.domain.event.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {

    private final Events events;

    @Autowired
    public EventsController(Events events) {
        this.events = events;
    }

    @RequestMapping(method = RequestMethod.GET, value = "events/next")
    public ResponseEntity<EventBean> getNextEntry(@RequestParam("id") Long id) {
        Event nextEvent = events.findTop1ByIdGreaterThanOrderByIdAsc(id)
                .orElseThrow(
                        () -> new ApiException("EVENT_ID", "Next event entry does not exist", HttpStatus.NOT_FOUND)
                );

        return ResponseEntity.ok(new EventBean(nextEvent));
    }
}
