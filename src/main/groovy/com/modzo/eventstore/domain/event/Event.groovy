package com.modzo.eventstore.domain.event

import groovy.transform.CompileStatic
import org.hibernate.validator.constraints.NotBlank

import javax.persistence.*

@CompileStatic
@Entity
@Table(name = 'events')
class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = 'id', nullable = false)
    Long id

    @NotBlank
    @Column(name = 'unique_id', nullable = false, unique = true, length = 100)
    String uniqueId

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = 'created', nullable = false)
    Date created = new Date()

    @NotBlank
    @Column(name = 'topic', nullable = false, length = 100)
    String topic

    @NotBlank
    @Column(name = 'value', nullable = false, columnDefinition = 'longtext')
    String value
}
