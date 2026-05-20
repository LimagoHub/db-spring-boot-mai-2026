package de.db.webapp.domain;


import de.db.webapp.domain.model.Schwein;

import java.util.Optional;
import java.util.UUID;

public interface SchweineService {

    void speichern(Schwein schwein) ;
    void aendern(Schwein schwein) ;
    Optional<Schwein> findeNachId(UUID id) ;
    Iterable<Schwein> findeAlle(Integer mindestGewicht) ;
    void loesche(UUID id) ;

}
