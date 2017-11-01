package com.modzo.eventstore.api.events.add;

import com.modzo.eventstore.domain.event.commands.add.AddEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static java.lang.String.format;

@RestController
public class AddEventController {

    private final AddEventHandler addEventHandler;

    @Autowired
    public AddEventController(AddEventHandler addEventHandler) {
        this.addEventHandler = addEventHandler;
    }

    @PostMapping(value = "events")
    public ResponseEntity addEvent(@RequestBody @Valid AddEventRequest addEventRequest) {
        addEventHandler.handle(addEventRequest.toAddEvent());
        return ResponseEntity.created(URI.create(format("events/%s", addEventRequest.getUniqueId()))).build();
    }
}
