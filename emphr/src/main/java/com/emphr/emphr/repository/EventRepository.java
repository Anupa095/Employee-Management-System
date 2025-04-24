package com.emphr.emphr.repository;

import com.emphr.emphr.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
