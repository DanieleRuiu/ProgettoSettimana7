package it.epicode.PrenotazioneEvento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.epicode.PrenotazioneEvento.model.PrenotazioneEvento;
import java.util.List;

@Repository
public interface PrenotazioneEventoRepository extends JpaRepository<PrenotazioneEvento, Long> {
    List<PrenotazioneEvento> findByUserId(Long userId);
    List<PrenotazioneEvento> findByEventName(String eventName);



}
