package it.epicode.PrenotazioneEvento.controller;



import it.epicode.PrenotazioneEvento.model.PrenotazioneEventoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import it.epicode.PrenotazioneEvento.model.PrenotazioneEvento;
import it.epicode.PrenotazioneEvento.service.PrenotazioneEventoService;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/bookingEvents")
@Validated
public class PrenotazioneEventoController {

    private final PrenotazioneEventoService bookingEventService;

    @Autowired
    public PrenotazioneEventoController(PrenotazioneEventoService bookingEventService) {
        this.bookingEventService = bookingEventService;
    }

    @GetMapping
    public ResponseEntity<List<PrenotazioneEvento>> findAll() {
        List<PrenotazioneEvento> bookingEvents = bookingEventService.findAll();
        return ResponseEntity.ok(bookingEvents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrenotazioneEvento> findById(@PathVariable Long id) {
        PrenotazioneEvento bookingEvent = bookingEventService.findById(id);
        return ResponseEntity.ok(bookingEvent);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<PrenotazioneEvento> save(@Valid @RequestBody PrenotazioneEvento bookingEvent) {
        PrenotazioneEvento savedBookingEvent = bookingEventService.save(new PrenotazioneEventoRequest());
        return new ResponseEntity<>(savedBookingEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PrenotazioneEvento> update(@PathVariable Long id, @Valid @RequestBody PrenotazioneEvento bookingEvent) {
        PrenotazioneEvento updatedBookingEvent = bookingEventService.update(id, bookingEvent);
        return ResponseEntity.ok(updatedBookingEvent);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookingEventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search")
    public ResponseEntity<List<PrenotazioneEvento>> findByEventName(@RequestParam String eventName) {
        List<PrenotazioneEvento> bookingEvents = bookingEventService.findByEventName(eventName);
        return ResponseEntity.ok(bookingEvents);
    }
}