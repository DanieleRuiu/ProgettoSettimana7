package it.epicode.PrenotazioneEvento.service;


import it.epicode.PrenotazioneEvento.model.Evento;
import it.epicode.PrenotazioneEvento.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.PrenotazioneEvento.exception.EntityNotFoundException;



import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventRepository;

    @Autowired
    public EventoService(EventoRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Evento createEvent(Evento newEvent) {

        if (newEvent.getData().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento non può essere nel passato");
        }
        return eventRepository.save(newEvent);
    }

    public Evento updateEvent(Long eventId, Evento updatedEvent) {
        return eventRepository.findById(eventId).map(event -> {
            event.setTitolo(updatedEvent.getTitolo());
            event.setDescrizione(updatedEvent.getDescrizione());
            event.setData(updatedEvent.getData());
            event.setLuogo(updatedEvent.getLuogo());
            event.setNumeroPostiDisponibili(updatedEvent.getNumeroPostiDisponibili());
            event.setTipo(updatedEvent.getTipo());

            return eventRepository.save(event);
        }).orElseThrow(() -> new EntityNotFoundException("Evento non trovato con id: " + eventId));
    }

    public void deleteEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new EntityNotFoundException("Evento non trovato con id: " + eventId);
        }
        eventRepository.deleteById(eventId);
    }

    public List<Evento> getAllEvents() {
        return eventRepository.findAll();
    }

    public Evento getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Evento non trovato con id: " + eventId));
    }



}
