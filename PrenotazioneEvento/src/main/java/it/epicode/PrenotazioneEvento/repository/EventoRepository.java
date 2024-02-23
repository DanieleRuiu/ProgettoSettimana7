package it.epicode.PrenotazioneEvento.repository;

import it.epicode.PrenotazioneEvento.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {


}
