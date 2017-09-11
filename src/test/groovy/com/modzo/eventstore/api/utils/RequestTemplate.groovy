package com.modzo.eventstore.api.utils

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

    def <T> ResponseEntity<T> post(String url, Map<String, String> request, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)

        HttpEntity<String> entity = new HttpEntity<String>(toJson(request), headers)

        return restTemplate.postForEntity(url, entity, clazz)
    }

    def <T> ResponseEntity<T> get(String url, Class<T> clazz) {
        return restTemplate.getForEntity(url, clazz)
    }
}
