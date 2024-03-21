package edu.miu.cs.cs544.controller;


import edu.miu.common.controller.BaseReadWriteController;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.domain.Record;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.EventService;
import edu.miu.cs.cs544.service.SessionService;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import edu.miu.cs.cs544.service.contract.SessionRequestPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController extends BaseReadWriteController<SessionPayload, Session, Long> {
    @Autowired
    private SessionService sessionService;



    @GetMapping("/{eventId}/sessions")
    public ResponseEntity<?> getSessionsByEventId(@PathVariable Long eventId) {
        List<SessionPayload> sessions = sessionService.getSessionsByEventId(eventId);
        if (sessions.isEmpty()) {
            throw new ResourceNotFoundException("No sessions found for eventId: " + eventId);
        }
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }
    @PostMapping("/{eventId}/sessions")
    public ResponseEntity<?> addSessionToEvent(@PathVariable Long eventId, @RequestBody SessionRequestPayload sessionRequestPayload) {
        SessionPayload sessionPayload = sessionService.addSessionToEvent(eventId, sessionRequestPayload);
        return new ResponseEntity<>(sessionPayload, HttpStatus.CREATED);
    }

    @PutMapping("/{eventId}/sessions/{sessionId}")
    public ResponseEntity<?> updateSession(@PathVariable Long eventId, @PathVariable Long sessionId, @RequestBody SessionRequestPayload sessionRequestPayload) {
        SessionPayload updatedSessionPayload = sessionService.updateSession(eventId, sessionId, sessionRequestPayload);
        return new ResponseEntity<>(updatedSessionPayload, HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}/sessions/{sessionId}")
    public ResponseEntity<?> deleteSession(@PathVariable Long eventId, @PathVariable Long sessionId) {
        sessionService.deleteSession(eventId, sessionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{eventId}/Record")
    public ResponseEntity<List<Record>> calculateRecord(@PathVariable Long eventId) {
        List<Record> recordList = eventService.calculateRecord(eventId);
        if (recordList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(recordList);
        }
    }
}

