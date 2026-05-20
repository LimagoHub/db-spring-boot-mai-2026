package de.db.webapp.domain.internal;


import de.db.webapp.domain.AlreadyExistsException;
import de.db.webapp.domain.NotFoundException;
import de.db.webapp.domain.SchweineService;
import de.db.webapp.domain.SchweineServiceException;
import de.db.webapp.domain.mapper.SchweinMapper;
import de.db.webapp.domain.model.Schwein;
import de.db.webapp.persistence.repository.SchweineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@RequiredArgsConstructor
public class SchweineServiceImpl implements SchweineService {


    private final SchweineRepository schweinRepository;

    private final SchweinMapper schweinMapper;



    @Override
    public void speichern(final Schwein schwein) {
        // Sicherstellen, dass das Objekt existiert
        validateNotNull(schwein);

        // Validierung: Ein neues Schwein darf keine ID haben, die schon existiert
        if (schwein.getId() != null && schweinRepository.existsById(schwein.getId())) {
            throw new AlreadyExistsException("Schwein existiert bereits: " + schwein.getId());
        }

        persist(schwein);
    }

    @Override
    public void aendern(final Schwein schwein) {

        // Sicherstellen, dass das Objekt existiert
        validateNotNull(schwein);

        // Validierung: Zum Ändern muss es existieren
        if (schwein.getId() == null || !schweinRepository.existsById(schwein.getId())) {
            throw new NotFoundException("Schwein existiert nicht und kann nicht aktualisiert werden.");
        }

        persist(schwein);
    }

    @Override
    public void loesche(final UUID id)  {
       if(! schweinRepository.existsById(id)) {
            throw new NotFoundException("Schwein existiert nicht");
        }
        try {
            schweinRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Es ist ein Fehler aufgetreten",e);
        }
    }

    @Override
    public Optional<Schwein> findeNachId(final UUID id)  {
        try {
            return schweinRepository.findById(id).map(schweinMapper::convert);
        }catch (Exception e) {
            throw new SchweineServiceException("Upps", e);
        }
    }

    @Override
    public Iterable<Schwein> findeAlle(Integer mindestGewicht)  {
        try {
            return schweinMapper.convert(schweinRepository.findByGewichtIsGreaterThanEqual(mindestGewicht));
        } catch (Exception e) {
            throw new SchweineServiceException("Upps", e);
        }
    }



    // Hilfsmethode zur Kapselung des Mappings und Speicherns
    private void persist(Schwein schwein) {
        try {

            schweinRepository.save(schweinMapper.convert(schwein));
        } catch (Exception e) {
            throw new SchweineServiceException("Datenbankfehler beim Sichern des Schweins", e);
        }
    }

    private void validateNotNull(Schwein schwein) {
        if (schwein == null) {
            throw new SchweineServiceException("Das Schwein darf nicht null sein.");
        }
    }
}
