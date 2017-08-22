package com.modzo.eventstore.domain.event

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

interface Events extends PagingAndSortingRepository<Event, Long> {

    Optional<Event> findByUniqueId(String uniqueId)

    @Query('SELECT e FROM Event e where e.id > :id')
    Optional<Event> findNextEventByOrderById(@Param('id') Long id)

}
