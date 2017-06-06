package com.modzo.eventstore.api.events.add;

import com.modzo.eventstore.domain.event.commands.add.AddEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class AddEventController {

    @Autowired
    private AddEventHandler addEventHandler;

    @PostMapping(value = "events")
    public ResponseEntity addEvent(@Valid AddEventRequest addEventRequest) {
        addEventHandler.handle(addEventRequest.toAddEvent());
        return ResponseEntity.created(URI.create(String.format("events/%s", addEventRequest.getId()))).build();
    }
}
