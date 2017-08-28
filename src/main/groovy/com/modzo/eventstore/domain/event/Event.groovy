package com.modzo.eventstore.domain.event

import groovy.transform.CompileStatic
import org.hibernate.validator.constraints.NotBlank

import javax.persistence.*

import static javax.persistence.GenerationType.SEQUENCE

@CompileStatic
@Entity
@Table(name = 'events')
class Event {
    @Id
    @GeneratedValue(generator = 'events_sequence', strategy = SEQUENCE)
    @SequenceGenerator(name = 'events_sequence', sequenceName = 'events_sequence', allocationSize = 1)
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
