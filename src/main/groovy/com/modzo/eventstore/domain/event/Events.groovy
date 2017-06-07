package com.modzo.eventstore.domain.event

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

interface Events extends PagingAndSortingRepository<Event, String> {

    Optional<Event> findByUniqueId(String uniqueId)

    @Query('SELECT e FROM Event e where e.id > :id')
    Page<Event> findNextEntry(@Param('id') String id, Pageable pageable)

}
