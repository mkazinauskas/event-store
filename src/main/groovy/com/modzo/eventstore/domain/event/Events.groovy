package com.modzo.eventstore.domain.event

import org.springframework.data.repository.PagingAndSortingRepository

interface Events extends PagingAndSortingRepository<Event, Long> {

    Optional<Event> findByUniqueId(String uniqueId)

    Optional<Event> findTop1ByIdGreaterThanOrderByIdAsc(Long id)
}
