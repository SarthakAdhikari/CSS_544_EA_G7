package edu.miu.cs.cs544.service;


import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Record;
import edu.miu.cs.cs544.service.contract.EventPayload;

import java.util.List;

// Remove @Component from here
public interface EventService extends BaseReadWriteService<EventPayload, Event, Long> {
   List<Record> calculateRecord(Long eventId);
}

