package com.modzo.eventstore.api

import com.modzo.eventstore.api.utils.TestContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract class ApiSpec extends Specification {

    @Autowired
    TestContext testContext
}
