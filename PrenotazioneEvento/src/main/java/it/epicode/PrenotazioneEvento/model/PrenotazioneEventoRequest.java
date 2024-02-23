package it.epicode.PrenotazioneEvento.model;

public class PrenotazioneEventoRequest {

    private Long id;
    private Long organizerEventId;

    public PrenotazioneEventoRequest() {
    }

    public PrenotazioneEventoRequest(Long id, Long organizerEventId) {
        this.id = id;
        this.organizerEventId = organizerEventId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvent() {
        return organizerEventId;
    }

    public void setEvent(Long organizerEventId) {
        this.organizerEventId = organizerEventId;
    }


}
