package de.db.webapp.domain;

import de.db.webapp.domain.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonenService {
    boolean speichern(Person person) throws PersonenServiceException;
    boolean aendern(Person person) throws PersonenServiceException;
    Optional<Person> findeNachId(UUID id)throws PersonenServiceException;
    Iterable<Person> findeAlle() throws PersonenServiceException;
    boolean loesche(UUID id) throws PersonenServiceException;
}
