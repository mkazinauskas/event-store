package com.modzo.eventstore.domain

class DomainException extends RuntimeException {
    final String id
    final String message

    DomainException(String id, String message) {
        super(message)
        this.id = id
        this.message = message
    }
}
