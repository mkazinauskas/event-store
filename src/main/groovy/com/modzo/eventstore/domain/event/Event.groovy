package com.modzo.eventstore.domain.event

import groovy.transform.CompileStatic
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldIndex
import org.springframework.data.elasticsearch.annotations.FieldType

@CompileStatic
@Document(indexName = 'event', type = 'event', refreshInterval = '-1')
class Event {
    @Id
    String id

    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    Date created = new Date()

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    String topic

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    String value
}
