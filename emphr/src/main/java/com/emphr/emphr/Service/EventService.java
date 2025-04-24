package com.emphr.emphr.service;

import com.emphr.emphr.entity.Event;
import com.emphr.emphr.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    // Save or Update Event
    public Event saveOrUpdateEvent(Event event) {
        return eventRepository.save(event);
    }

    // Get All Events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get Event by ID
    public Event getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElseThrow(() -> new RuntimeException("Event not found"));
    }

    // Delete Event by ID
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
