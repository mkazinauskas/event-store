package com.modzo.eventstore.api.utils

import org.springframework.stereotype.Component

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric

@Component
class DummyEvent {

    private final TestContext testContext

    DummyEvent(TestContext testContext) {
        this.testContext = testContext
    }

    Map<String, String> sampleRequest() {
        [
                uniqueId: randomAlphanumeric(100),
                topic   : 'topic',
                value   : 'value'
        ]
    }

    void create(Map<String, String> request = sampleRequest()) {
        testContext.createEvent(request)
    }
}
