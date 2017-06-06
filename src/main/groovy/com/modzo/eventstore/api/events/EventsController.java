package com.modzo.eventstore.api.events;

import com.modzo.eventstore.api.ApiException;
import com.modzo.eventstore.domain.event.Event;
import com.modzo.eventstore.domain.event.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventsController {

    @Autowired
    private Events events;

    @RequestMapping(method = RequestMethod.GET, value = "events")
    public ResponseEntity<Page<EventBean>> getEvents(Pageable pageable) {
        Page<Event> entries = events.findAll(pageable);
        return new ResponseEntity<>(entries.map(EventBean::new), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "events/{id}")
    public ResponseEntity<EventBean> getEvents(@PathVariable("id") String uniqueId) {
        Event event = events.findById(uniqueId).orElseThrow(
                () -> new ApiException("EVENT_ID", "Event with specified does not exist", HttpStatus.NOT_FOUND)
        );
        return ResponseEntity.ok(new EventBean(event));
    }

    @RequestMapping(method = RequestMethod.GET, value = "events/next")
    public ResponseEntity<EventBean> getNextEntry(@RequestParam("id") String uniqueId) {
        Event event = events.findById(uniqueId)
                .orElseThrow(
                        () -> new ApiException("EVENT_ID", "Event with specified does not exist", HttpStatus.NOT_FOUND)
                );

        Pageable first = new PageRequest(0, 1, Sort.Direction.ASC, "id");

        Event nextEvent = events.findNextEntry(event.getId(), first).getContent().stream().findFirst()
                .orElseThrow(
                        () -> new ApiException("EVENT_ID", "Next event entry does not exist", HttpStatus.NOT_FOUND)
                );

        return ResponseEntity.ok(new EventBean(nextEvent));
    }
}
