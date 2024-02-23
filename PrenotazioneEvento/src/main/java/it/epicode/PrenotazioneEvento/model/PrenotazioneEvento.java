package it.epicode.PrenotazioneEvento.model;

import jakarta.persistence.*;




import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "booking_event")
@NamedQuery(name = "BookingEvent.findBookingByEventId", query = "SELECT b FROM BookingEvent b WHERE b.event.id = :id")
@Data
public class PrenotazioneEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utente user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Evento event;

    private LocalDateTime bookingDate;

    public PrenotazioneEvento() {
    }

    public PrenotazioneEvento(Utente user, Evento event, LocalDateTime bookingDate) {
        this.user = user;
        this.event = event;
        this.bookingDate = bookingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUser() {
        return user;
    }

    public void setUser(Utente user) {
        this.user = user;
    }

    public Evento getEvent() {
        return event;
    }

    public void setEvent(Evento event) {
        this.event = event;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}
