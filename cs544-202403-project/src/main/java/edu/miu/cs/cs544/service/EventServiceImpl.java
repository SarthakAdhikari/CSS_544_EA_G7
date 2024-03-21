package edu.miu.cs.cs544.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Record;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.repository.RecordRepository;
import edu.miu.cs.cs544.service.contract.EventPayload;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl extends BaseReadWriteServiceImpl<EventPayload, Event, Long> implements EventService {

    private final RecordRepository recordRepository;
    private final EventRepository eventRepository;

    public EventServiceImpl(RecordRepository recordRepository, EventRepository eventRepository) {
        this.recordRepository = recordRepository;
        this.eventRepository = eventRepository;
    }


    @Override
    public List<Record> calculateRecord(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            // Updated method call to match the repository
            return recordRepository.findByScannerEventId(eventId);
        }
        return Collections.emptyList(); // Return an empty list if the event is not found
    }
}
