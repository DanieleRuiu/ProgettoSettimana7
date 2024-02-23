package it.epicode.PrenotazioneEvento.controller;

import it.epicode.PrenotazioneEvento.model.Evento;
import it.epicode.PrenotazioneEvento.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/events")
public class EventoController {

    private final EventoService eventService;

    @Autowired
    public EventoController(EventoService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Evento> createEvent(@RequestBody Evento newEvent) {
        Evento event = eventService.createEvent(newEvent);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Evento> updateEvent(@PathVariable Long eventId, @RequestBody Evento updatedEvent) {
        Evento event = eventService.updateEvent(eventId, updatedEvent);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEvents() {
        List<Evento> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Evento> getEventById(@PathVariable Long eventId) {
        Evento event = eventService.getEventById(eventId);
        return ResponseEntity.ok(event);
    }


}

