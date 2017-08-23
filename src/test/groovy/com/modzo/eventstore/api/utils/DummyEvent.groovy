package com.modzo.eventstore.api.utils

import com.modzo.eventstore.api.events.add.AddEventRequest
import org.springframework.stereotype.Component

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric
import static org.apache.commons.lang.RandomStringUtils.randomAscii

@Component
class DummyEvent {

    private final TestContext testContext

    DummyEvent(TestContext testContext) {
        this.testContext = testContext
    }

    static AddEventRequest sampleRequest() {
        new AddEventRequest(
                uniqueId: randomAlphanumeric(100),
                type: randomAlphanumeric(100),
                data: randomAscii(10000)
        )
    }

    void create(AddEventRequest request = sampleRequest()) {
        testContext.createEvent(request, String)
    }
}
