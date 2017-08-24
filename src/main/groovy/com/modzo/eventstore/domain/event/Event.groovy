package com.modzo.eventstore.domain.event

import groovy.transform.CompileStatic
import org.hibernate.validator.constraints.NotBlank

import javax.persistence.*

@CompileStatic
@Entity
@Table(name = 'events')
class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id', nullable = false)
    Long id

    @NotBlank
    @Column(name = 'unique_id', nullable = false, unique = true, length = 100)
    String uniqueId

    @NotBlank
    @Column(name = 'type', nullable = false, length = 100)
    String type

    @NotBlank
    @Column(name = 'data', nullable = false, columnDefinition = 'clob')
    String data
}
