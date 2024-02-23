package it.epicode.PrenotazioneEvento.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "booking-event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id")
    )
    private Set<Utente> utenti;
    private String name;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int numeroPostiDisponibili;
    @Enumerated(EnumType.STRING)
    private TipoEvento tipo;



    public Evento() {
    }

    public Evento(String name, String descrizione, LocalDate data, String luogo, int numeroPostiDisponibili, TipoEvento tipo) {
        this.name = name;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.numeroPostiDisponibili = numeroPostiDisponibili;
        this.tipo = tipo;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return name;
    }

    public void setTitolo(String titolo) {
        this.name= titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public int getNumeroPostiDisponibili() {
        return numeroPostiDisponibili;
    }

    public void setNumeroPostiDisponibili(int numeroPostiDisponibili) {
        this.numeroPostiDisponibili = numeroPostiDisponibili;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "OrganizerEvent{" +
                "id=" + id +
                ", titolo='" + name + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", data=" + data +
                ", luogo='" + luogo + '\'' +
                ", numeroPostiDisponibili=" + numeroPostiDisponibili +
                ", tipo=" + tipo +
                '}';
    }




}
