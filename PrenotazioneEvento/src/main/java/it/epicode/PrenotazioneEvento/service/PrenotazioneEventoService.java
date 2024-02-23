package it.epicode.PrenotazioneEvento.service;


import it.epicode.PrenotazioneEvento.model.Utente;
import it.epicode.PrenotazioneEvento.repository.EventoRepository;
import it.epicode.PrenotazioneEvento.repository.UtenteRepository;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import it.epicode.PrenotazioneEvento.model.PrenotazioneEvento;
import it.epicode.PrenotazioneEvento.repository.PrenotazioneEventoRepository;
import it.epicode.PrenotazioneEvento.exception.EntityNotFoundException;
import it.epicode.PrenotazioneEvento.exception.BadRequestException;
import it.epicode.PrenotazioneEvento.model.PrenotazioneEventoRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import it.epicode.PrenotazioneEvento.model.Evento;
import java.time.LocalDateTime;
import jakarta.persistence.Persistence;


@Service
public class PrenotazioneEventoService {

    private final EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;


    private final PrenotazioneEventoRepository bookingEventRepository;

    @Autowired
    private EventoRepository eventRepository;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @Autowired
    private UtenteRepository userRepository;

    @Autowired
    public PrenotazioneEventoService(PrenotazioneEventoRepository bookingEventRepository) {
        this.bookingEventRepository = bookingEventRepository;
        this.entityManagerFactory = Persistence.createEntityManagerFactory("PrenotazioneEventi");
        this.entityManager = entityManagerFactory.createEntityManager();

    }

    public List<PrenotazioneEvento> findAll() {
        return bookingEventRepository.findAll();
    }

    public PrenotazioneEvento findById(Long id) {
        return bookingEventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Prenotazione Evento con id " + id + " non trovato"));
    }

    public PrenotazioneEvento save(PrenotazioneEventoRequest bookingEventRequest) {
    UserDetails authenticationUser= (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Utente user = userRepository.findByUsername(authenticationUser.getUsername()).orElseThrow(() -> new EntityNotFoundException("Utente con id " + authenticationUser.getUsername() + " non trovato"));

        Query query = entityManager.createQuery("SELECT e FROM BookingEvent e WHERE e.event_id = :event_id");
        query.setParameter("event_id", bookingEventRequest.getEvent());

        List<PrenotazioneEvento> bookingEvent = query.getResultList();

     Evento event = eventRepository.findById(bookingEventRequest.getEvent()).orElseThrow(() -> new EntityNotFoundException("Evento con id " + bookingEventRequest.getEvent() + " non trovato"));
            int numeroPostiDisponibili = event.getNumeroPostiDisponibili() - bookingEvent.size();
            if (numeroPostiDisponibili < 1) throw new BadRequestException("Non ci sono posti disponibili per l'evento selezionato");

        PrenotazioneEvento newBookingEvent = new PrenotazioneEvento(user, event, LocalDateTime.now());

        bookingEventRepository.save(newBookingEvent);
        return newBookingEvent;
    }







    public void delete(PrenotazioneEventoRequest bookingEventRequest) {
        UserDetails authenticationUser= (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utente user = userRepository.findByUsername(authenticationUser.getUsername()).orElseThrow(() -> new EntityNotFoundException("User with ID " + authenticationUser.getUsername() + " not found"));
        Query query = entityManager.createQuery("SELECT e FROM BookingEvent e WHERE e.event_id = :event_id");
        query.setParameter("event_id", bookingEventRequest.getEvent());

        List<PrenotazioneEvento> bookingEvent = query.getResultList();

        Evento event = eventRepository.findById(bookingEventRequest.getEvent()).orElseThrow(() -> new EntityNotFoundException("Event with ID " + bookingEventRequest.getEvent() + " not found"));

        Query query2 = entityManager.createQuery("SELECT e FROM BookingEvent e WHERE e.user_id = :user_id AND e.event_id = :event_id");
        query2.setParameter("user_id", user.getId());
        query2.setParameter("event_id", event.getId());
        List<PrenotazioneEvento> bookingEvent2 = query2.getResultList();
        if (bookingEvent2.isEmpty()) throw new BadRequestException("Utenza non prenotata per l'evento selezionato");
        bookingEventRepository.deleteById(bookingEvent2.get(0).getId());


    }

    //metodo findByEventName

    public List<PrenotazioneEvento> findByEventName(String eventName) {
        if (eventName == null) {
            throw new EntityNotFoundException("Il nome dell'evento non può essere nullo");
        }
        return bookingEventRepository.findByEventName(eventName);
    }

    public void deleteById(Long id) {
        bookingEventRepository.deleteById(id);
    }

    public PrenotazioneEvento update(Long id, PrenotazioneEvento bookingEvent) {
        return bookingEventRepository.findById(id).map(bookingEvent1 -> {
            bookingEvent1.setEvent(bookingEvent.getEvent());
            bookingEvent1.setUser(bookingEvent.getUser());
            bookingEvent1.setBookingDate(bookingEvent.getBookingDate());
            return bookingEventRepository.save(bookingEvent1);
        }).orElseThrow(() -> new EntityNotFoundException("Prenotazione evento con id " + id + " non trovato"));
    }
}
