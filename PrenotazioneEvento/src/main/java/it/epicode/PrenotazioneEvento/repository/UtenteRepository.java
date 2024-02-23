package it.epicode.PrenotazioneEvento.repository;

import it.epicode.PrenotazioneEvento.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Optional<Utente> findByUsername(String username);
    Optional<Utente> findByEmail(String email);




}
