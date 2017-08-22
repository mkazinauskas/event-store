package com.modzo.eventstore.api.utils

import com.modzo.eventstore.api.events.EventBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

import static groovy.json.JsonOutput.toJson

@Component
class RequestTemplate {

    private TestRestTemplate restTemplate

    RequestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate
    }

    ResponseEntity<String> post(String url, Map<String, String> request) {
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        HttpEntity<String> entity = new HttpEntity<String>(toJson(request), headers)

        return restTemplate.postForEntity(url, entity, String)
    }

    ResponseEntity<EventBean> get(String url) {
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        return restTemplate.getForEntity(url, EventBean)
    }
}
