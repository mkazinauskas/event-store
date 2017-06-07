package com.modzo.eventstore.api

import com.modzo.eventstore.domain.DomainException

class Error {
    final String id
    final String message

    Error(String id, String message) {
        this.id = id
        this.message = message
    }

    Error(DomainException domainException) {
        this.id = domainException.id
        this.message = domainException.message
    }

    Error(ApiException apiException) {
        this.id = apiException.id
        this.message = apiException.message
    }
}