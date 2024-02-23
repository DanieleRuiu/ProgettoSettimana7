package it.epicode.PrenotazioneEvento.model;

import java.time.LocalDate;

public class EventoRequest {
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int numeroPostiDisponibili;
    private TipoEvento tipo;
    // Getters and Setters

    public String getTitolo() {
        return titolo;
    }

    public  void setTitolo(String titolo) {
        this.titolo = titolo;
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


    // toString method

    @Override
    public String toString() {
        return "OrganizerEventRequest{" +
                "titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", data=" + data +
                ", luogo='" + luogo + '\'' +
                ", numeroPostiDisponibili=" + numeroPostiDisponibili +
                ", tipo=" + tipo +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoRequest that = (EventoRequest) o;
        return numeroPostiDisponibili == that.numeroPostiDisponibili && titolo.equals(that.titolo) && descrizione.equals(that.descrizione) && data.equals(that.data) && luogo.equals(that.luogo) && tipo == that.tipo;
    }
}
