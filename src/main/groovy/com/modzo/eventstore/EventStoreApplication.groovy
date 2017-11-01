package com.modzo.eventstore

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class EventStoreApplication {

    static void main(String[] args) {
        SpringApplication.run EventStoreApplication, args
    }
}
